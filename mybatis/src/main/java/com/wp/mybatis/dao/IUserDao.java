package com.wp.mybatis.dao;

import com.wp.mybatis.domain.QueryVo;
import com.wp.mybatis.domain.User;

import java.util.List;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 22:31 2020/7/8
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    void saveUser(User user);

    void updateUser(User user);


    void deleteUser(Integer userId);

    User findByid(Integer userId);

    User findByName(String username);

    int findTotal();

    int findByVo(QueryVo vo);
}
