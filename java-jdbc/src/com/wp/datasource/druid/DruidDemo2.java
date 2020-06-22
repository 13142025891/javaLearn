package com.wp.datasource.druid;

import com.wp.datasource.utils.JdbcUtils;

import java.sql.*;
import java.util.Date;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 22:07 2020/6/10
 */
public class DruidDemo2 {
    public static void main(String[] args) {
        PreparedStatement stmt = null;
        Connection con = null;
        try {
            con = JdbcUtils.getConnection();
            String sql = "insert into student VALUES(null,?,14,500,?,null)";

            stmt = con.prepareStatement(sql);
            stmt.setString(1, "大神");
            Date dt = new java.util.Date();
            stmt.setDate(2, new java.sql.Date(dt.getTime()));

            int r = stmt.executeUpdate();
            System.out.println(r);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JdbcUtils.close(stmt,con);
        }


    }
}
