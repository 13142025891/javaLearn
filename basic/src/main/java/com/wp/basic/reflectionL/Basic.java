package com.wp.basic.reflectionL;

public class Basic {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(String.class);

        String s="Hello";
        System.out.println(s.getClass());

        Class cls=Class.forName("java.lang.String");
        System.out.println(cls);

        System.out.println();
    }
}
