package wl1929.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import wl1929.travel.domain.ResultInfo;
import wl1929.travel.domain.User;
import wl1929.travel.service.UserService;
import wl1929.travel.service.impl.UserServiceImpl;
import wl1929.travel.web.servlet.BaseServlet.BaseServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 14:11
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    // 声明UserService业务对象
    private UserService service = new UserServiceImpl();

    /**
     * 注册功能
     * @author : wangli4773@163.com
     * @date : 2020/7/29 14:24
     * @param request :
     * @param response :
     * @return : void
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 验证校验
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        // 为了保证验证码只能使用一次
        session.removeAttribute("CHECKCODE_SERVER");

        // 失败逻辑
        if (null == checkcode_server || !checkcode_server.equalsIgnoreCase(check)) {
            // 验证码错误
            ResultInfo info = new ResultInfo();
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            // 将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        // 1.获取数据
        Map<String, String[]> map = request.getParameterMap();

        // 2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 3.调用service完成注册
        UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();

        // 4.响应结果
        if (flag) {
            // 注册成功
            info.setFlag(true);
        } else {
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败!");
        }

        // 将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //将json数据写回客户端
        //设置content-type
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 登录功能
     * @author : wangli4773@163.com
     * @date : 2020/7/29 14:26
     * @param request :
     * @param response :
     * @return : void
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 1.获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();

        // 2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 3.调用service完成注册
        UserService service = new UserServiceImpl();
        User user1 = service.login(user);

        ResultInfo info = new ResultInfo();

        // 4.判断用户对象是否为null
        if (null == user1) {
            // 用户名密码或错误
            info.setFlag(false);
            info.setErrorMsg("用户名密码或错误");
        }

        // 5.判断用户是否激活
        if (null != user1 && "Y".equals(user1.getStatus())) {
            // 用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活");
        }

        // 6.判断登录成功
        if (null != user1 && "Y".equals(user1.getStatus())) {
            // 登录成功标记
            request.getSession().setAttribute("user", user1);
            // 登录成功
            info.setFlag(true);
        }

        // 响应数据
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(), info);
    }

    /**
     * 查询单个对象
     * @author : wangli4773@163.com
     * @date : 2020/7/29 14:28
     * @param request :
     * @param response :
     * @return : void
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端
        writeValue(user, response);
    }

    /**
     * 退出功能
     * @author : wangli4773@163.com
     * @date : 2020/7/29 14:30
     * @param request :
     * @param response :
     * @return : void
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.销毁session
        request.getSession().invalidate();
        // 2.跳转登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    /**
     * 激活功能
     * @author : wangli4773@163.com
     * @date : 2020/7/29 14:30
     * @param request :
     * @param response :
     * @return : void
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1.获取激活码
        String code = request.getParameter("code");
        if (null != code) {
            // 2.调用service完成激活
            boolean flag = service.active(code);

            // 3.判断标记
            String msg = null;
            if (flag) {
                // 激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            } else {
                // 激活失败
                msg = "激活失败，请联系管理员!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}
