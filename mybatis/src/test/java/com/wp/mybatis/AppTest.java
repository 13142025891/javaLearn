package com.wp.mybatis;


import com.wp.mybatis.dao.IUserDao;
import com.wp.mybatis.domain.QueryVo;
import com.wp.mybatis.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao mapper;

    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession
        sqlSession = factory.openSession();
        //4.使用SqlSession接口的代理对象
        mapper = sqlSession.getMapper(IUserDao.class);

    }

    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() {

        //5.使用代理对象执行
        List<User> all = mapper.findAll();
        all.forEach(System.out::println);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("ceshi last id");
        user.setAddress("beiing");
        user.setBirthday(new Date());
        user.setSex("男");
        mapper.saveUser(user);
        System.out.println(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(49);
        user.setUsername("wangwu");
        user.setAddress("beiing");
        user.setBirthday(new Date());
        user.setSex("男");
        mapper.updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        mapper.deleteUser(48);
    }

    @Test
    public void testFindByid() {
        User byid = mapper.findByid(49);
        System.out.println(byid);
    }


    @Test
    public void testFindByName() {
        User byid = mapper.findByName("%wu%");
        System.out.println(byid);
    }


    @Test
    public void testFindTotal() {
        int total=mapper.findTotal();
        System.out.println(total);
    }


    @Test
    public void testfindByVo() {
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("%wu%");
        queryVo.setUser(user);

        int total=mapper.findByVo(queryVo);
        System.out.println(total);
    }
}
