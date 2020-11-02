package com.wp.springioc.service;

import javax.sound.midi.Soundbank;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 12:05 2020/11/1
 */

public class UserService {

    private static OrderService orderService
            = new OrderService();

    //private String name;

    public UserService(String name, String hobby) {
        System.out.println("init UserService " + name);
        System.out.println(name + "  " + hobby);
        // this.name = name;
    }

    public void GetName() {
        System.out.println("my name is wp!");
    }

    public static OrderService createInstance() {
        return orderService;
    }
}
