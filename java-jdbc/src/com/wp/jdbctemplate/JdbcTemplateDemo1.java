package com.wp.jdbctemplate;

import com.wp.datasource.utils.JdbcUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 22:34 2020/6/10
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        var sql="update student set score=166 where id= ?";
        var r=template.update(sql,1);


        //.query()
        System.out.println(r);
    }
}
