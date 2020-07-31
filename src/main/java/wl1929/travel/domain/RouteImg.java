package wl1929.travel.domain;

import java.io.Serializable;

/**
 * @Description: 旅游线路图片实体类
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 16:30
 */
public class RouteImg implements Serializable {

    /**
     * 商品图片id
     */
    private int rgid;

    /**
     * 旅游商品id
     */
    private int rid;

    /**
     * 详情商品大图
     */
    private String bigPic;

    /**
     * 详情商品小图
     */
    private String smallPic;

    /**
     * 无参构造方法
     * @author : wangli4773@163.com
     * @date : 2020/7/29 16:31
     * @return : null
     */
    public RouteImg() {
    }

    /**
     * 有参构造方法
     * @author : wangli4773@163.com
     * @date : 2020/7/29 16:32
     * @param rgid :
     * @param rid :
     * @param bigPic :
     * @param smallPic :
     * @return : null
     */
    public RouteImg(int rgid, int rid, String bigPic, String smallPic) {
        this.rgid = rgid;
        this.rid = rid;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
    }

    public int getRgid() {
        return rgid;
    }

    public void setRgid(int rgid) {
        this.rgid = rgid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }
}
