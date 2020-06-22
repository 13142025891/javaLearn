package com.wp.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 22:50 2020/6/9
 */
public class C3p0Demo1 {
    public static void main(String[] args) throws SQLException {
        //创建数据库连接对象
        DataSource ds=new ComboPooledDataSource();
        Connection con=ds.getConnection();

        System.out.println(con);
    }
}
