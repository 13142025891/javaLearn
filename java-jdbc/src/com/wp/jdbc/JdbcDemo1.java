package com.wp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @Author: WuPna
 * @Description: jdbc快速入门
 * @Date: Create in 21:11 2020/6/8
 */
public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        //1. 导入驱动jar包 mysql-connector-java-5.1.37-bin.jar
        //1.新建 libs目录
        //2.复制jar包到目录下，右键目录 add as library
        //注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //获取数据库连接对象
        Connection con = DriverManager.getConnection("jdbc:mysql:///db1", "root", "123");
        //定义sql
        String sql = "update student set score=600 where id = 1 ";
        //获取执行sql的对象  返回值是修改的行数
        Statement stmt = con.createStatement();
        //处理结果
        int count=stmt.executeUpdate(sql);
        System.out.println(count);
        //释放资源
        stmt.close();
        con.close();
    }
}
