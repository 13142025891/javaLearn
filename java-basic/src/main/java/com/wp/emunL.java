package com.wp;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Arrays;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 21:30 2021/2/2
 */
public class emunL {

    public static void main(String[] args) {


        Arrays.asList( Season.values()).stream().forEach(p->System.out.println(p.ordinal()));


        System.out.println(Season.valueOf("SPRING"));

        Season season=Season.valueOf("SPRING");
        season.setName("秋天");
    }


    public enum Season{
        SPRING,SUMMER,FALL,WINTER;

        public  String name;
        public void setName(String name){
            switch (this){
                case SPRING:
                    if(name.equals("春")){
                        this.name=name;
                    }else{
                        System.out.println("error");
                        return;
                    }
                    break;
            }
        }
    }
}
