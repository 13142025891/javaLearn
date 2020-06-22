package com.wp.web.login;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 15:39 2020/6/21
 */
public class UserDao {

    private JdbcTemplate template = new JdbcTemplate(JdbcUtil.getDataSource());


    public User login(User loginUser) {
        User user = null;
        try {
            String sql = "select * from user where username=? and password=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class),
                    loginUser.getUsername(), loginUser.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }


}
