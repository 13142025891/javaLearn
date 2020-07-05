package com.wp.web.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 14:51 2020/7/5
 */
public class JedisTest {

    @Test
    public void test1(){
        Jedis jedis=new Jedis("localhost",6379);
        jedis.set("username","lisi");
        //jedis.expire()
        System.out.println(jedis.get("a"));
        jedis.close();
    }
    @Test
    public void test2(){
        Jedis jedis=new Jedis("localhost",6379);

        jedis.hset("person","name","lisi");
        jedis.hset("person","age","32");


        String age=jedis.hget("person","age");
        System.out.println(age);
        Map<String, String> person = jedis.hgetAll("person");

        person.entrySet().forEach(item->{

            System.out.println("key:"+item.getKey()+" value:"+item.getValue());

        });

        jedis.close();
    }


    @Test
    public void testPool(){
        //默认配置
        //JedisPool pool=new JedisPool();

        //指定配置
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxTotal(500);
        JedisPool pool=new JedisPool(config,"localhost",6379);

        Jedis jedis = pool.getResource();
        jedis.set("age","12");
        jedis.close();

    }

}
