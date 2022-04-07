package core.util;

import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnUtil {

    private ConnUtil(){
    }

    public static ConnUtil connUtil = new ConnUtil();

    public static ConnUtil getInstance(){
        return connUtil;
    }

    private final ThreadLocal<Connection> conn = new ThreadLocal<>();

    public Connection get(){
        Connection druidPooledConnection = conn.get();
        if(druidPooledConnection == null){
            try {
                Connection connection = DataSourceUtil.getSource().getConnection();
                conn.set(connection);
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return druidPooledConnection;
    }
}
