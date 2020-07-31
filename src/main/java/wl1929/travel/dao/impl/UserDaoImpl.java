package wl1929.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import wl1929.travel.dao.UserDao;
import wl1929.travel.domain.User;
import wl1929.travel.util.JDBCUtils;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/28 15:21
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据用户名查询用户信息
     * @author : wangli4773@163.com
     * @date : 2020/7/29 9:00
     * @param username :
     * @return : wl1929.travel.domain.User
     */
    @Override
    public User findByUsername(String username) {
        User user = null;
        // 1.定义sql
        String sql = "select * from tab_user where username = ?";
        // 2.执行sql
        user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        return user;
    }

    /**
     * 保存用户信息
     * @author : wangli4773@163.com
     * @date : 2020/7/29 9:03
     * @param user :
     * @return : void
     */
    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email, status, code) values(?,?,?,?,?,?,?,?,?)";
        template.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());
    }

    /**
     * 根据激活码查询用户对象
     * @author : wangli4773@163.com
     * @date : 2020/7/29 9:08
     * @param code :
     * @return : wl1929.travel.domain.User
     */
    @Override
    public User findByCode(String code) {
        User user = null;
        String sql = "select * from tab_user where code = ?";
        user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        return user;
    }

    /**
     * 修改指定用户激活状态
     * @author : wangli4773@163.com
     * @date : 2020/7/29 9:10
     * @param user :
     * @return : void
     */
    @Override
    public void updateStatus(User user) {
        String sql = " update tab_user set status = 'Y' where uid=?";
        template.update(sql,user.getUid());
    }

    /**
     * 根据用户名和密码查询的方法
     * @author : wangli4773@163.com
     * @date : 2020/7/29 9:10
     * @param username :
     * @param password :
     * @return : wl1929.travel.domain.User
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        String sql = "select * from tab_user where username = ? and password = ?";
        user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        return user;
    }
}
