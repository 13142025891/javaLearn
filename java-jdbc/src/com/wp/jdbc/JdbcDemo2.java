package com.wp.jdbc;

import java.sql.*;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 20:57 2020/6/9
 */
public class JdbcDemo2 {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet r = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            var sql = "insert into student VALUES(null,'李留',14,500,CURRENT_DATE,null)";
            con = DriverManager.getConnection("jdbc:mysql:///db1", "root", "123");
            stmt = con.createStatement();
            var result = stmt.executeUpdate(sql);
            System.out.println("insert:" + result);
            sql = "update student set score=200 where id=1";
            result = stmt.executeUpdate(sql);
            System.out.println("update:" + result);
            sql = "delete from student where id=4";
            result = stmt.executeUpdate(sql);
            System.out.println("delete:" + result);
            sql = "select * from student";
            r = stmt.executeQuery(sql);
            while (r.next()) {
                System.out.println("id:" + r.getInt(1) + " name:" +
                        r.getString("name") + " birthday:" + r.getDate("birthday"));
            }

            sql="select * from user where username =?";
            var pstmt=con.prepareStatement(sql);
            pstmt.setString(1, "lisi");

            r = pstmt.executeQuery();

            while (r.next()) {
                System.out.println("id:" + r.getInt(1) + " username:" +
                        r.getString("username"));
            }




        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (r != null) {
                try {
                    r.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
