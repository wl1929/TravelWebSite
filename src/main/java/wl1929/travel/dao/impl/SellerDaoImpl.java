package wl1929.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import wl1929.travel.dao.SellerDao;
import wl1929.travel.domain.Seller;
import wl1929.travel.util.JDBCUtils;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/30 09:23
 */
public class SellerDaoImpl implements SellerDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据id查询
     * @author : wangli4773@163.com
     * @date : 2020/7/30 9:25
     * @param id :
     * @return : wl1929.travel.domain.Seller
     */
    @Override
    public Seller findById(int id) {
        String sql = "select * from tab_seller where sid = ? ";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), id);
    }
}
