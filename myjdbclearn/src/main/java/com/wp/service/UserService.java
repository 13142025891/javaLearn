package com.wp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Component
public class UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUserByID(long id) {
        return jdbcTemplate.execute((Connection con) -> {

            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM users where id=?")) {
                ps.setObject(1, id);
                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        return new User(rs.getLong("id"),
                                rs.getString("email"),
                                rs.getString("pass"),
                                rs.getString("name")
                        );
                    }

                    throw new RuntimeException("user not found by id");


                }
            }

        });
    }

    public User getUserByName(String name) {
        return jdbcTemplate.execute("select * from users where name=?", (PreparedStatement ps) -> {
            ps.setObject(1, name);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new User(rs.getLong("id"),
                            rs.getString("email"),
                            rs.getString("pass"),
                            rs.getString("name")
                    );
                }

                throw new RuntimeException("user not found by id");


            }
        });
    }

    public User getUserByEmail(String email) {
        return jdbcTemplate.queryForObject("select * from users where email=?", new Object[]{email},
                (ResultSet rs, int number) -> {
                    if (rs.next()) {
                        return new User(rs.getLong("id"),
                                rs.getString("email"),
                                rs.getString("pass"),
                                rs.getString("name")
                        );
                    }

                    throw new RuntimeException("user not found by id");
                });
    }

    public long getUsers(){
        return jdbcTemplate.queryForObject("",null,ResultSet::getLong);
    }


    public List<User> getUsers(int pageIndex){
        int limit=100;
        int offset=limit*(pageIndex-1);

        return jdbcTemplate.query("select * from users LIMIT ? OFFSET ?",new Object[]{limit,offset},
                new BeanPropertyRowMapper<>(User.class));
    }
}
