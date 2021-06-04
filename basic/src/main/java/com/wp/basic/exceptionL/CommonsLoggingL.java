package com.wp.basic.exceptionL;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CommonsLoggingL {

    public static void main(String[] args) {
        Log log = LogFactory.getLog(CommonsLoggingL.class);

        log.error("fail...");
        log.info("start...");
        log.warn("end.");
    }
}
