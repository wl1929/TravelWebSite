package wl1929.travel.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description: Jedis工具类
 * @Author wangli4773@163.com
 * @Created: 2020/07/29 15:19
 */
public final class JedisUtil {

    private static JedisPool jedisPool;

    static {
        // 读取配置文件
        InputStream is = JedisPool.class.getClassLoader().getResourceAsStream("jedis.properties");
        // 创建Properties对象
        Properties properties = new Properties();
        // 关联文件
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取数据，设置到JedisPoolConfig中
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));

        //初始化JedisPool
        jedisPool = new JedisPool(config,  properties.getProperty("host"), Integer.parseInt(properties.getProperty("port")));
    }

    /**
     * 获取连接方法
     * @author : wangli4773@163.com
     * @date : 2020/7/29 15:27

     * @return : redis.clients.jedis.Jedis
     */
    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 关闭Jedis
     * @author : wangli4773@163.com
     * @date : 2020/7/29 15:28
     * @param jedis :
     * @return : void
     */
    public static void close(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }
}
