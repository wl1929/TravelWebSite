package wl1929.travel.service.impl;

import wl1929.travel.dao.FavoriteDao;
import wl1929.travel.dao.RouteDao;
import wl1929.travel.dao.RouteImgDao;
import wl1929.travel.dao.SellerDao;
import wl1929.travel.dao.impl.FavoriteDaoImpl;
import wl1929.travel.dao.impl.RouteDaoImpl;
import wl1929.travel.dao.impl.RouteImgDaoImpl;
import wl1929.travel.dao.impl.SellerDaoImpl;
import wl1929.travel.domain.PageBean;
import wl1929.travel.domain.Route;
import wl1929.travel.domain.RouteImg;
import wl1929.travel.domain.Seller;
import wl1929.travel.service.RouteService;

import java.util.List;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 16:41
 */
public class RouteServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();

    private RouteImgDao routeImgDao = new RouteImgDaoImpl();

    private SellerDao sellerDao = new SellerDaoImpl();

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    /**
     * 根据类别进行分页查询
     * @author : wangli4773@163.com
     * @date : 2020/7/30 9:47
     * @param cid :
     * @param currentPage :
     * @param pageSize :
     * @param rname :
     * @return : wl1929.travel.domain.PageBean<wl1929.travel.domain.Route>
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        // 封装PageBean
        PageBean<Route> pageBean = new PageBean<>();
        // 设置当前页码
        pageBean.setCurrentPage(currentPage);
        // 设置每页显示条数
        pageBean.setPageSize(pageSize);
        // 设置总记录数
        int totalCount = routeDao.findTotalCount(cid, rname);
        pageBean.setTotalCount(totalCount);
        // 设置当前页显示的数据集合
        // 开始的记录数
        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid, start, pageSize, rname);
        pageBean.setList(list);
        // 设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    /**
     * 根据id查询
     * @author : wangli4773@163.com
     * @date : 2020/7/30 9:47
     * @param rid :
     * @return : wl1929.travel.domain.Route
     */
    @Override
    public Route findOne(String rid) {
        // 1.根据id去route表中查询route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));
        // 2.根据route的id 查询图片集合信息
        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());
        // 2.2将集合设置到route对象
        route.setRouteImgList(routeImgList);
        // 3.根据route的sid（商家id）查询商家对象
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        // 4. 查询收藏次数
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);

        return route;
    }
}
