package com.wp.demo01;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 23:06 2020/5/27
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        Thread.currentThread().setName("线程2");
        System.out.println("我实现 Runnable 接口,我的名字叫："+Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
