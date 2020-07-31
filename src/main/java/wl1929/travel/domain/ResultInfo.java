package wl1929.travel.domain;

import java.io.Serializable;

/**
 * @Description: 用于封装后端返回前端数据对象
 * @Author wangli4773@163.com
 * @Created: 2020/07/28 14:45
 */
public class ResultInfo implements Serializable {

    /**
     * 后端返回结果正常为true，发生异常返回false
     */
    private boolean flag;

    /**
     * 后端返回结果数据对象
     */
    private Object data;

    /**
     * 发生异常的错误消息
     */
    private String errorMsg;

    /**
     * 无参构造方法
     * @author : wangli4773@163.com
     * @date : 2020/7/28 14:55
     * @return : null
     */
    public ResultInfo() {}

    /**
     * 有参构造方法
     * @author : wangli4773@163.com
     * @date : 2020/7/28 14:51
     * @param flag :
     * @return : null
     */
    public ResultInfo(boolean flag) {
        this.flag = flag;
    }

    /**
     * 有参构造方法
     * @author : wangli4773@163.com
     * @date : 2020/7/28 14:52
     * @param flag :
     * @param errorMsg :
     * @return : null
     */
    public ResultInfo(boolean flag, String errorMsg) {
        this.flag = flag;
        this.errorMsg = errorMsg;
    }

    /**
     * 有参构造方法
     * @author : wangli4773@163.com
     * @date : 2020/7/28 14:53
     * @param flag :
     * @param data :
     * @param errorMsg :
     * @return : null
     */
    public ResultInfo(boolean flag, Object data, String errorMsg) {
        this.flag = flag;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
