package wl1929.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import wl1929.travel.dao.RouteDao;
import wl1929.travel.domain.Route;
import wl1929.travel.util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 17:13
 */
public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据cid查询总记录数
     * @author : wangli4773@163.com
     * @date : 2020/7/29 17:15
     * @param cid :
     * @param rname :
     * @return : int
     */
    @Override
    public int findTotalCount(int cid, String rname) {
        // 1.定义sql模板
        String sql = "select count(*) from tab_route where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);

        // 条件
        List params = new ArrayList();
        // 2.判断参数是否有值
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }

        if (null != rname && rname.length() > 0) {
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }

        sql = sb.toString();

        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    /**
     * 根据cid，start,pageSize查询当前页的数据集合
     * @author : wangli4773@163.com
     * @date : 2020/7/29 17:15
     * @param cid :
     * @param start :
     * @param pageSize :
     * @param rname :
     * @return : java.util.List<wl1929.travel.domain.Route>
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        String sql = " select * from tab_route where 1 = 1 ";
        // 1.定义sql模板
        StringBuilder sb = new StringBuilder(sql);

        // 条件
        List params = new ArrayList();
        // 2.判断参数是否有值
        if (cid != 0) {
            sb.append(" and cid = ? ");
            params.add(cid);
        }

        if (null != rname && rname.length() > 0) {
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }

        // 分页条件
        sb.append(" limit ? , ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);

        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }

    /**
     * 根据id查询
     * @author : wangli4773@163.com
     * @date : 2020/7/29 17:15
     * @param rid :
     * @return : wl1929.travel.domain.Route
     */
    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
    }
}
