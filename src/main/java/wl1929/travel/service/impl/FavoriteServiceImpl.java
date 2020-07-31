package wl1929.travel.service.impl;

import wl1929.travel.dao.FavoriteDao;
import wl1929.travel.dao.impl.FavoriteDaoImpl;
import wl1929.travel.domain.Favorite;
import wl1929.travel.service.FavoriteService;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/30 09:58
 */
public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    /**
     * 判断是否收藏
     * @author : wangli4773@163.com
     * @date : 2020/7/30 9:59
     * @param rid :
     * @param uid :
     * @return : boolean
     */
    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);

        // 如果对象有值，则为true，反之，则为false
        return favorite != null;
    }

    /**
     * 添加收藏
     * @author : wangli4773@163.com
     * @date : 2020/7/30 9:59
     * @param rid :
     * @param uid :
     * @return : void
     */
    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid), uid);
    }
}
