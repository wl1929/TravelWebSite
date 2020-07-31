package wl1929.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import wl1929.travel.dao.FavoriteDao;
import wl1929.travel.domain.Favorite;
import wl1929.travel.util.JDBCUtils;

import java.util.Date;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/30 09:27
 */
public class FavoriteDaoImpl implements FavoriteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据rid和uid查询收藏信息
     * @author : wangli4773@163.com
     * @date : 2020/7/30 9:33
     * @param rid :
     * @param uid :
     * @return : wl1929.travel.domain.Favorite
     */
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        String sql = " select * from tab_favorite where rid = ? and uid = ?";
        favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        return favorite;
    }

    /**
     * 根据rid 查询收藏次数
     * @author : wangli4773@163.com
     * @date : 2020/7/30 9:33
     * @param rid :
     * @return : int
     */
    @Override
    public int findCountByRid(int rid) {
        String sql = "SELECT COUNT(*) FROM tab_favorite WHERE rid = ?";
        return template.queryForObject(sql, Integer.class, rid);
    }

    /**
     * 添加收藏
     * @author : wangli4773@163.com
     * @date : 2020/7/30 9:34
     * @param rid :
     * @param uid :
     * @return : void
     */
    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";
        template.update(sql, rid, new Date(), uid);
    }
}
