package wl1929.travel.service.impl;

import wl1929.travel.dao.UserDao;
import wl1929.travel.dao.impl.UserDaoImpl;
import wl1929.travel.domain.User;
import wl1929.travel.service.UserService;
import wl1929.travel.util.MailUtils;
import wl1929.travel.util.UuidUtil;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/28 15:16
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     * @author : wangli4773@163.com
     * @date : 2020/7/29 9:23
     * @param user :
     * @return : boolean
     */
    @Override
    public boolean regist(User user) {
        // 1.根据用户名查询用户对象
        User user1 = userDao.findByUsername(user.getUsername());
        // 判断u是否为null
        if(null != user1){
            // 用户名存在，注册失败
            return false;
        }
        // 2.保存用户信息
        // 2.1设置激活码，唯一字符串
        user.setCode(UuidUtil.getUuid());
        // 2.2设置激活状态
        user.setStatus("N");
        userDao.save(user);
        // 3.激活邮件发送
        String content = "<a href='http://localhost/travel/activeUserServlet?code=" + user.getCode() + "'>点击激活【黑马旅游网】</a>";
        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        return true;
    }

    /**
     * 激活用户
     * @author : wangli4773@163.com
     * @date : 2020/7/29 10:19
     * @param code :
     * @return : boolean
     */
    @Override
    public boolean active(String code) {
        // 1.根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if (null != user) {
            // 2.调用dao的修改激活状态的方法
            userDao.updateStatus(user);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 登录方法
     * @author : wangli4773@163.com
     * @date : 2020/7/29 10:21
     * @param user :
     * @return : wl1929.travel.domain.User
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
