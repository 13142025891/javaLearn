package com.wp.demo01.FunctionalInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 21:17 2020/6/2
 */
public class FunctionalInterface {
    public void a(){
        List<String>  list= new ArrayList<>();
        list.stream();


    }
    public FunctionalInterface(){

    }
    public FunctionalInterface(int a){

    }

    public Integer add(int a,int b){
        return a;
    }

    public void eat(Eatable e){

        System.out.println(e);
        e.taste();
    }

    public void test(ttt e){

        System.out.println(e);
    }
    public void test1(test1 e){

        System.out.println(e);
        //e.n(1,2);
    }

    public static void main(String[] args) {

        Eatable t=()-> System.out.println("sdfsdf");

        t.taste();

        FunctionalInterface f=new FunctionalInterface();

        f.eat(()-> System.out.println("123"));

        f.test(FunctionalInterface::new);

        test1 ttt=FunctionalInterface::add;


    }

    @java.lang.FunctionalInterface
    interface test1{
        Integer n(FunctionalInterface a,int b,int c);
    }
    @java.lang.FunctionalInterface
    interface ttt{
        FunctionalInterface n();
    }

    interface Eatable{
        void taste();
    }


}
