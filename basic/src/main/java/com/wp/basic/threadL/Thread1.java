package com.wp.basic.threadL;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 8:52 2021/6/24
 */
public class Thread1 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new MyThread();
        t.start();
        Thread.sleep(1000);
        t.interrupt(); // 中断t线程
        t.join(); // 等待t线程结束
        System.out.println("end1");
    }


    static class MyThread extends Thread {
        @Override
        public void run() {
            Thread hello = new HelloThread();
            hello.start(); // 启动hello线程
            try {
                 hello.join(); // 等待hello线程结束
            } catch (InterruptedException e) {
                System.out.println("interrupted!");
            }
           //hello.interrupt();
        }
    }

    static class HelloThread extends Thread {
        @Override
        public void run() {
            int n = 0;
            while (!isInterrupted()) {
                n++;
                System.out.println(n + " hello!");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("HelloThread exception");
                    break;
                }
            }
        }
    }

}
