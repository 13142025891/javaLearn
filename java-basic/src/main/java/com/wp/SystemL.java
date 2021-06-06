package com.wp;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 23:37 2021/2/2
 */
public class SystemL {

    public static void main(String[] args) {
        System.getenv().forEach((k,v)->{
            System.out.println("k:"+k+" v:"+v);
        });
    }
}
