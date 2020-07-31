package wl1929.travel.dao;

import wl1929.travel.domain.Favorite;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/30 09:27
 */
public interface FavoriteDao {

    public Favorite findByRidAndUid(int rid, int uid);

    public int findCountByRid(int rid);

    void add(int i, int uid);
}
