package wl1929.travel.dao;

import wl1929.travel.domain.RouteImg;

import java.util.List;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/30 09:17
 */
public interface RouteImgDao {

    public List<RouteImg> findByRid(int rid);
}
