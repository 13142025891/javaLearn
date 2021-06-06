package com.wp;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 22:05 2021/2/2
 */
public class GcLearn {


    // java -verbose:gc com.wp.GcLearn  可以查看gc 信息
    public static void main(String[] args) {
        for  (int i=0;i<4;i++){
            new GcLearn();

            Runtime.getRuntime().gc();
        }

    }

    @Override
    public void finalize(){
        System.out.println("系统清理了");
    }
}
