package wl1929.travel.service;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/30 09:58
 */
public interface FavoriteService {

    public boolean isFavorite(String rid, int uid);

    void add(String rid, int uid);
}
