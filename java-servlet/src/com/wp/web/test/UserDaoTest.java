package com.wp.web.test;

import com.wp.web.login.User;
import com.wp.web.login.UserDao;
import org.junit.Test;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 15:57 2020/6/21
 */
public class UserDaoTest {
    @Test
    public void TestLogin(){
        UserDao dao=new UserDao();

        User user = new User();
        user.setUsername("wangwu1");
        user.setPassword("123");
        User login = dao.login(user);
        System.out.println(login);


    }
}
