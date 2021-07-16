package com.wp.basic.lambdaL;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        basicL[] array = new basicL[]{new basicL()};
        // 请使用忽略大小写排序，并改写为方法引用:
        Arrays.sort(array, basicL::compareTo);
        //System.out.println(String.join(", ", array));


        byte[] data = {-1, 101, 108, 108, 111, 33};

        byte[] bytes = new byte[]{-76, -1, 32, 30, 36};
        System.out.println(bytes);
        char[] chars = getChars(bytes);
        System.out.println(chars);

        byte[] bytes2 = getBytes(chars);

        char[] chars2 = getChars(bytes2);




       /* System.out.println((char) -1);
        try (InputStream input = new ByteArrayInputStream(data)) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println((char) n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    /**
     * char转byte
     */
    public static byte[] getBytes(char[] chars) {


        int len = chars.length;
        byte[] bytes = new byte[len];

        for (int i = 0; i < len; i++) {
            bytes[i] = (byte) (chars[i]);
        }
        return bytes;
    }

    /**
     * byte转char
     */
    public static char[] getChars(byte[] bytes) {

        int len = bytes.length;
        char[] chars = new char[len];

        for (int i = 0; i < len; i++) {
            chars[i] = (char) (bytes[i] & 0xff);
        }
        return chars;


    }
}
