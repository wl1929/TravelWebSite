package wl1929.travel.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Description:
 * @Author wangli4773@163.com
 * @Created: 2020/07/28 15:27
 */
public class JDBCUtils {

    // 1.声明静态数据源成员变量
    private static DataSource ds;

    // 2.创建连接池对象
    static {
        // 加载配置文件中的数据
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties pp = new Properties();
        try {
            pp.load(is);
            // 创建连接池，使用配置文件中的参数
            ds = DruidDataSourceFactory.createDataSource(pp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3.定义公有的得到数据源的方法
    public static DataSource getDataSource() {
        return ds;
    }

    // 4.定义得到连接对象的方法
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    // 5.定义关闭资源的方法
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (null != resultSet) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {}
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {}
        }
    }

    // 6.重载关闭方法
    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }
}
