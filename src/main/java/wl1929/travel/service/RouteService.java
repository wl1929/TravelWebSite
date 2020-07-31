package wl1929.travel.service;

import wl1929.travel.domain.PageBean;
import wl1929.travel.domain.Route;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 16:14
 */
public interface RouteService {

    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    public Route findOne(String rid);
}
