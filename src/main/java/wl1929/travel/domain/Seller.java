package wl1929.travel.domain;

import java.io.Serializable;

/**
 * @Description: 商家实体类
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 16:19
 */
public class Seller implements Serializable {

    /**
     * 商家id
     */
    private int sid;

    /**
     * 商家名称
     */
    private String sname;

    /**
     * 商家电话
     */
    private String consphone;

    /**
     * 商家地址
     */
    private String address;

    /**
     * 无参构造方法
     * @author : wangli4773@163.com
     * @date : 2020/7/29 16:28
     * @return : null
     */
    public Seller() {
    }

    /**
     * 构造方法
     * @author : wangli4773@163.com
     * @date : 2020/7/29 16:29
     * @param sid :
     * @param sname :
     * @param consphone :
     * @param address :
     * @return : null
     */
    public Seller(int sid, String sname, String consphone, String address) {
        this.sid = sid;
        this.sname = sname;
        this.consphone = consphone;
        this.address = address;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getConsphone() {
        return consphone;
    }

    public void setConsphone(String consphone) {
        this.consphone = consphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
