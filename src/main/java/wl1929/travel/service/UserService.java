package wl1929.travel.service;

import wl1929.travel.domain.User;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/28 15:14
 */
public interface UserService {

    boolean regist(User user);

    boolean active(String code);

    User login (User user);
}
