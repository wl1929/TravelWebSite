package wl1929.travel.domain;

import java.io.Serializable;

/**
 * @Description: 收藏实体类
 * @Author wangli4773@163.com
 * @Created: 2020/07/30 09:28
 */
public class Favorite implements Serializable {

    /**
     * 旅游线路对象
     */
    private Route route;

    /**
     * 收藏时间
     */
    private String date;

    /**
     * 所属用户
     */
    private User user;

    /**
     * 无参构造方法
     * @author : wangli4773@163.com
     * @date : 2020/7/30 9:31
     * @return : null
     */
    public Favorite() {
    }

    /**
     * 有参构造方法
     * @author : wangli4773@163.com
     * @date : 2020/7/30 9:31
     * @param route :
     * @param date :
     * @param user :
     * @return : null
     */
    public Favorite(Route route, String date, User user) {
        this.route = route;
        this.date = date;
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
