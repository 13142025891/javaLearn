package com.wp.web.servlet.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 9:35 2020/6/21
 */
@WebServlet("/demo3")
public class ServletRequestDemo3 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String line=null;
        while ((line=reader.readLine())!=null){
            System.out.println(line);
        }
    }


}
