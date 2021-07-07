package com.wp.learnjava.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");

        String name = req.getParameter("name");
        if (name == null) name = "world";  //这里我对null的情况进行了处理，好像没什么效果

        PrintWriter writer = resp.getWriter();
        writer.write("Hello "+name);
        writer.flush();
    }
}
