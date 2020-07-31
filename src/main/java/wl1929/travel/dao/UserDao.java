package wl1929.travel.dao;

import wl1929.travel.domain.User;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/28 15:18
 */
public interface UserDao {

    public User findByUsername(String username);

    public void save(User user);

    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}
