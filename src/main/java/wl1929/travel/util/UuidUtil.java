package wl1929.travel.util;

import java.util.UUID;

/**
 * @Description: 产生UUID随机字符串工具类
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 09:26
 */
public final class UuidUtil {
    private UuidUtil() {}
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
