package com.wp.learnjava.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

// 注解配置，还可以xml 配置
@WebServlet(urlPatterns = "/app/")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //解决中文乱码 必须获取之前就设置
        // 可以通过全局设置
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String name=req.getParameter("name");
        System.out.println("getRequestURL:"+req.getRequestURL());


        System.out.printf("getContextPath:  %s",req.getContextPath());
        //Field[] declaredFields = req.getClass().getDeclaredFields();
       /* Arrays.stream(declaredFields).forEach(p->{
            try {
                p.setAccessible(true);
                System.out.printf("%s:%s", p.getName(),p.get(req));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });*/

        req.getSession().setAttribute("a",name);
        resp.sendRedirect("/hello.jsp");

        /*resp.sendRedirect("/test?name="+name);

        // 设置响应类型:
        resp.setContentType("text/html");
        // 获取输出流:
        PrintWriter pw = resp.getWriter();
        // 写入响应:
        pw.write( String.format("<h1>Hello, world %s!</h1>",name));
        // 最后不要忘记flush强制输出:
        pw.flush();*/
    }
}
