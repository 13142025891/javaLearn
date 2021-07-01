package com.wp.basic.lambdaL;

import java.util.Arrays;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 8:14 2021/6/29
 */
public class basicL {
        public int compareTo(basicL s2) {
            return 1;
        }

        public static void main(String[] args) {
            //String[] array = new String[] { "apple", "Orange", "banana", "Lemon" };
            basicL[] array = new basicL[] { new basicL() };
            // 请使用忽略大小写排序，并改写为方法引用:
            Arrays.sort(array, basicL::compareTo);
            //System.out.println(String.join(", ", array));
        }

}
