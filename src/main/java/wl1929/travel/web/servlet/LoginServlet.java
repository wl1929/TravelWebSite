//package wl1929.travel.web.servlet;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.beanutils.BeanUtils;
//import wl1929.travel.domain.ResultInfo;
//import wl1929.travel.domain.User;
//import wl1929.travel.service.UserService;
//import wl1929.travel.service.impl.UserServiceImpl;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.util.Map;
//
///**
// * @Description: 用户登录
// * @Author wangli4773@163.com
// * @Created: 2020/07/29 13:29
// */
//@WebServlet("/loginServlet")
//public class LoginServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // 1.获取用户名和密码数据
//        Map<String, String[]> map = request.getParameterMap();
//
//        // 2.封装对象
//        User user = new User();
//        try {
//            BeanUtils.populate(user, map);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//
//        // 3.调用service完成注册
//        UserService service = new UserServiceImpl();
//        User user1 = service.login(user);
//
//        ResultInfo info = new ResultInfo();
//
//        // 4.判断用户对象是否为null
//        if (null == user1) {
//            // 用户名密码或错误
//            info.setFlag(false);
//            info.setErrorMsg("用户名密码或错误");
//        }
//
//        // 5.判断用户是否激活
//        if (null != user1 && "Y".equals(user1.getStatus())) {
//            // 用户尚未激活
//            info.setFlag(false);
//            info.setErrorMsg("您尚未激活，请激活");
//        }
//
//        // 6.判断登录成功
//        if (null != user1 && "Y".equals(user1.getStatus())) {
//            // 登录成功标记
//            request.getSession().setAttribute("user", user1);
//            // 登录成功
//            info.setFlag(true);
//        }
//
//        // 响应数据
//        ObjectMapper mapper = new ObjectMapper();
//
//        response.setContentType("application/json;charset=utf-8");
//        mapper.writeValue(response.getOutputStream(), info);
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doPost(request, response);
//    }
//}
