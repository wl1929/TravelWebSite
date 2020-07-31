package wl1929.travel.web.servlet;

import wl1929.travel.dao.impl.FavoriteDaoImpl;
import wl1929.travel.domain.PageBean;
import wl1929.travel.domain.Route;
import wl1929.travel.domain.User;
import wl1929.travel.service.FavoriteService;
import wl1929.travel.service.RouteService;
import wl1929.travel.service.impl.FavoriteServiceImpl;
import wl1929.travel.service.impl.RouteServiceImpl;
import wl1929.travel.web.servlet.BaseServlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @Description: 旅游线路查询
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 16:12
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();

    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 分页查询
     * @author : wangli4773@163.com
     * @date : 2020/7/30 10:11
     * @param request :
     * @param response :
     * @return : void
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        // 接受rname线路名称
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");

        // 类别id
        int cid = 0;
        // 2.处理参数
        if (null != cidStr && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }

        // 当前页码，如果不传递，则默认为第一页
        int currentPage = 0;
        if (null != currentPageStr && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }

        // 每页显示条数，如果不传递，默认每页显示5条记录
        int pageSize = 0;
        if (null != pageSizeStr && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }

        // 3.调用service查询PageBean对象
        PageBean<Route> pageBean = routeService.pageQuery(cid, currentPage, pageSize, rname);

        // 4.将pageBean对象序列化为json，返回
        writeValue(pageBean, response);
    }

    /**
     * 根据id查询一个旅游线路的详细信息
     * @author : wangli4773@163.com
     * @date : 2020/7/30 10:27
     * @param request :
     * @param response :
     * @return : void
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.接收id
        String rid = request.getParameter("rid");
        // 2.调用service查询route对象
        Route route = routeService.findOne(rid);
        // 3.转为json写回客户端
        writeValue(route,response);
    }

    /**
     * 判断当前登录用户是否收藏过该线路
     * @author : wangli4773@163.com
     * @date : 2020/7/30 10:28
     * @param request :
     * @param response :
     * @return : void
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 1.获取线路id
        String rid = request.getParameter("rid");

        //2. 获取当前登录的用户 user
        User user = (User) request.getSession().getAttribute("user");

        // 用户id
        int uid;
        if(null == user){
            // 用户尚未登录
            uid = 0;
        }else{
            // 用户已经登录
            uid = user.getUid();
        }

        // 3.调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);

        // 4.写回客户端
        writeValue(flag, response);
    }

    /**
     * 添加收藏
     * @author : wangli4773@163.com
     * @date : 2020/7/30 10:31
     * @param request :
     * @param response :
     * @return : void
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) {

        // 1.获取线路rid
        String rid = request.getParameter("rid");

        // 2.获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");

        // 用户id
        int uid;
        if(null == user){
            // 用户尚未登录
            return;
        }else{
            // 用户已经登录
            uid = user.getUid();
        }

        // 3.调用service添加
        favoriteService.add(rid, uid);
    }
}
