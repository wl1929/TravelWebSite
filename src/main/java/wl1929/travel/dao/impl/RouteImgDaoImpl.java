package wl1929.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import wl1929.travel.dao.RouteImgDao;
import wl1929.travel.domain.RouteImg;
import wl1929.travel.util.JDBCUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/30 09:18
 */
public class RouteImgDaoImpl implements RouteImgDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据route的id查询图片
     * @author : wangli4773@163.com
     * @date : 2020/7/30 9:20
     * @param rid :
     * @return : java.util.List<wl1929.travel.domain.RouteImg>
     */
    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql = "select * from tab_route_img where rid = ? ";
        return template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
    }
}
