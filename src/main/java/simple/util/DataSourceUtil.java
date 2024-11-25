package simple.util;

import com.alibaba.druid.pool.DruidDataSource;

public class DataSourceUtil {

    private static volatile DruidDataSource druidDataSource;

    public static DruidDataSource getSource(){
        if(druidDataSource == null){
            synchronized (DataSourceUtil.class){
                if(druidDataSource == null){
                    druidDataSource = new DruidDataSource();
                    druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
                    druidDataSource.setUrl("jdbc:mysql:///wxt");
                    druidDataSource.setUsername("root");
                    druidDataSource.setPassword("root");
                }
            }
        }

        return druidDataSource;
    }
}
