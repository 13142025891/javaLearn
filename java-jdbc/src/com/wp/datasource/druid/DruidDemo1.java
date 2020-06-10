package com.wp.datasource.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 21:44 2020/6/10
 */
public class DruidDemo1 {
    public static void main(String[] args) {
        Properties pro = new Properties();
        var is = DruidDemo1.class.getClassLoader().getResourceAsStream(
                "druid.properties"
        );
        try {
            pro.load(is);
            var ds = DruidDataSourceFactory.createDataSource(pro);
            var con = ds.getConnection();
            System.out.println(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
