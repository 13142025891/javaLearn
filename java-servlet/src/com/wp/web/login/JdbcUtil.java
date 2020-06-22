package com.wp.web.login;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 15:39 2020/6/21
 */
public class JdbcUtil {

    private static DataSource ds;
    static {
        Properties pro=new Properties();
        try {
            pro.load(JdbcUtil.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds= DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static DataSource getDataSource(){
        return  ds;
    }


    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }



}
