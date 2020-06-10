package com.wp.datasource.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Author: WuPna
 * @Description: Druid 连接池工具类
 * @Date: Create in 21:59 2020/6/10
 */
public class JdbcUtils {
    private static DataSource ds;

    static {

        Properties pro = new Properties();
        try {
            pro.load(JdbcUtils.class.getClassLoader().getResourceAsStream(
                    "druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    /**
     * 释放资源
     * @param stmt
     * @param con
     */
    public static void close(Statement stmt,Connection con) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            if(con!=null){
            try {
                //归还到连接池
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * 释放资源
     * @param rs
     * @param stmt
     * @param con
     */
    public static void close(ResultSet rs,Statement stmt, Connection con) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(stmt,con);
    }

    /**
     * 获取连接池
     * @return
     */
    public static  DataSource getDataSource(){
        return ds;
    }

}
