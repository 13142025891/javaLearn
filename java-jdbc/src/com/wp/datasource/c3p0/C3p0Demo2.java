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
public class C3p0Demo2 {
    public static void main(String[] args) throws SQLException {
        //创建数据库连接对象
        DataSource ds=new ComboPooledDataSource();

        for (int i = 0; i < 11; i++) {
            Connection con=ds.getConnection();
            System.out.println(i+":"+con);
            if(i==5){
                con.close();
            }
        }



    }
}
