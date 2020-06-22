package com.wp.web.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 9:35 2020/6/21
 */
@WebServlet("/demo2")
public class ServletRequestDemo2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getMethod());
        System.out.println(req.getContextPath());
        System.out.println(req.getServletPath());
        System.out.println(req.getQueryString());
        System.out.println(req.getRequestURI());
        System.out.println(req.getRequestURL());
        System.out.println(req.getProtocol());
        System.out.println(req.getRemoteAddr());


        System.out.println("-----------------");
        Enumeration<String> names = req.getHeaderNames();
        while (names.hasMoreElements()){
            System.out.println(names.nextElement());
        }

        System.out.println(req.getHeader("user-agent"));


    }


}
