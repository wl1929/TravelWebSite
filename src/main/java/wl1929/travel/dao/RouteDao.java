package wl1929.travel.dao;

import wl1929.travel.domain.Route;

import java.util.List;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 16:54
 */
public interface RouteDao {

    public int findTotalCount(int cid, String rname);

    public List<Route> findByPage(int cid, int start, int pageSize, String rname);

    public Route findOne(int rid);
}
