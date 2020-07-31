package wl1929.travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import wl1929.travel.dao.CategoryDao;
import wl1929.travel.domain.Category;
import wl1929.travel.util.JDBCUtils;

import java.util.List;

/**
 * @Description: 查询所有
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 15:37
 */
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";
        return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
    }
}
