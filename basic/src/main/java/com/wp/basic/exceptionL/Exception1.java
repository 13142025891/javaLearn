package com.wp.basic.exceptionL;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exception1 {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Exception1.class.getName());
        logger.setLevel(Level.SEVERE);
        //String s="abs";
        //int n=Integer.parseInt(s);
        //int num=0;  -enableassertions
        //assert  num>1:"num must > 1";

        byte[] bs = new byte[0];
        try {
            bs = toGBK();
        } catch (UnsupportedEncodingException e) {
            logger.warning("i am info");
            logger.severe("i am info");

            //e.printStackTrace();
        }
        catch (AssertionError e) {
            logger.warning("i am info");
            //logger.severe(()->e.getMessage());

            //e.printStackTrace();
        }
        System.out.println(Arrays.toString(bs));


    }

    static byte[] toGBK() throws UnsupportedEncodingException {
        int num=0;
        assert  num>1:"num must > 1";
        return "中文".getBytes("GBK");
    }
}
