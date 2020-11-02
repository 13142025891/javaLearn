package com.wp.springioc;

import com.wp.springioc.service.OrderService;
import com.wp.springioc.service.UserService;

import javax.sound.midi.Soundbank;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 12:07 2020/11/1
 */
public class UserController {

    public void setOrderService(OrderService orderService) {
        System.out.println("setOrderService");
        this.orderService = orderService;
    }

    private OrderService orderService;

    private UserService userService;
    public UserController(UserService userService){
        System.out.println("init UserController");
        this.userService=userService;

        userService.GetName();
    }
}
