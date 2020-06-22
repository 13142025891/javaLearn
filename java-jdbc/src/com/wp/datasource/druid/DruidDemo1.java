package com.wp.datasource.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 21:44 2020/6/10
 */
public class DruidDemo1 {
    public static void main(String[] args) {
        Properties pro = new Properties();
       InputStream is = DruidDemo1.class.getClassLoader().getResourceAsStream(
                "druid.properties"
        );
        try {
            pro.load(is);
            DataSource ds = DruidDataSourceFactory.createDataSource(pro);
            Connection con = ds.getConnection();
            System.out.println(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
