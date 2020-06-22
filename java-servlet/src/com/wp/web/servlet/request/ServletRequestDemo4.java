package com.wp.web.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 9:35 2020/6/21
 */
@WebServlet("/demo4")
public class ServletRequestDemo4 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post请求参数");
        System.out.println("username:"+req.getParameter("username"));
        System.out.println("----------fsdf--------");
        String[] likes = req.getParameterValues("like");
        Arrays.stream(likes).forEach(System.out::println);
        System.out.println("------------------");
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            System.out.println(name);
            System.out.println(req.getParameter(name));
            System.out.println("-----");
        }
        System.out.println("---------map---------");
        Map<String, String[]> parameterMap = req.getParameterMap();
        parameterMap.entrySet().forEach(item->{
            System.out.println("key:"+item.getKey());
            Arrays.stream(item.getValue()).forEach(System.out::println);

        });

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
