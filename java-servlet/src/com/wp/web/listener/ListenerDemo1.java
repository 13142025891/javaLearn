package com.wp.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 22:52 2020/7/2
 */
@WebListener
public class ListenerDemo1 implements ServletContextListener {
    /**
     * 销毁之前调用
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    /**
     * 创建后调用
     * @param servletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
