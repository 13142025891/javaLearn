package com.wp.basic.threadL.synchronizedL;

public class Thread12 {

    public static void main(String[] args) throws InterruptedException {
        Athread a = new Athread();
        Bthread b = new Bthread();


        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println(Counter.count);


    }
}

class Counter{
    public static final Object lock=new Object();
    public static int count=0;
}

class Athread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.lock){
                Counter.count++;
            }

        }
    }
}
class Bthread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (Counter.lock) {
                Counter.count--;
            }
        }
    }
}
