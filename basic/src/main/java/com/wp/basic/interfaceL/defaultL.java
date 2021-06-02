package com.wp.basic.interfaceL;





public class defaultL implements Parent {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void run() {
        System.out.println("i am default");
    }


    static class StaticNested {
        void hello() {
            System.out.println("Hello, " );
        }
    }

    public static void main(String[] args) {
        Parent p=new defaultL();
        p.run();
    }

    class Outer {
        private String name;

        Outer(String name) {
            this.name = name;
        }

        void asyncHello() {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    System.out.println("Hello, " + Outer.this.name);
                }
            };
            new Thread(r).start();
        }
    }


}
