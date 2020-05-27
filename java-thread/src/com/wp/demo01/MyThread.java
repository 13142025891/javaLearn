package com.wp.demo01;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 22:58 2020/5/27
 *
 *         创建多线程程序的第一种方式:创建Thread类的子类
 *         java.lang.Thread类:是描述线程的类,我们想要实现多线程程序,就必须继承Thread类
 *
 *         实现步骤:
 *         1.创建一个Thread类的子类
 *         2.在Thread类的子类中重写Thread类中的run方法,设置线程任务(开启线程要做什么?)
 *         3.创建Thread类的子类对象
 *         4.调用Thread类中的方法start方法,开启新的线程,执行run方法
 *        void start() 使该线程开始执行；Java 虚拟机调用该线程的 run 方法。
 *         结果是两个线程并发地运行；当前线程（main线程）和另一个线程（创建的新线程,执行其 run 方法）。
 *         多次启动一个线程是非法的。特别是当线程已经结束执行后，不能再重新启动。
 *         java程序属于抢占式调度,那个线程的优先级高,那个线程优先执行;同一个优先级,随机选择一个执行
 * 知识点：
 *      1.获取线程名字
 *      2.设置线程名字
 *
 *
 */

public class MyThread  extends  Thread{
    @Override
    public void run() {
        Thread.currentThread().setName("线程1");
        System.out.println("我是子线程,我的名字叫："+Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
