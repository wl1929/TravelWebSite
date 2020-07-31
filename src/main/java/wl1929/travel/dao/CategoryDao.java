package wl1929.travel.dao;

import wl1929.travel.domain.Category;

import java.util.List;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 15:36
 */
public interface CategoryDao {

    public List<Category> findAll();
}
