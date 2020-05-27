package com.wp.demo01;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 22:50 2020/5/27
 */
public class Demo01Thread {
    public static void main(String[] args) {
        System.out.println("我是 主线程");

        //开启一个空线程，什么都不做
       Thread t=new Thread();
       t.start();
       //使用一个自定义线程
        MyThread myThread = new MyThread();
        myThread.start();

        //使用Runnable
        MyRunnable myRunnable = new MyRunnable();
        Thread t1=new Thread(myRunnable);
        t1.start();

        //使用匿名内部类
        Thread t2=new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("我是匿名内部类");
            }
        });
        t2.start();



    }
}
