package com.wp.basic.interfaceL;

public interface Parent {
    String getName();

    default void run(){
        System.out.println("i am parent");
    }
}
