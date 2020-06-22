package com.wp.web.servlet.response;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 20:09 2020/6/21
 */
@WebServlet("/re5")
public class ServletContextDemo5 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        String realPath = servletContext.getRealPath("/a.txt"); //web目录下
        String realPath1 = servletContext.getRealPath("/WEB-INF/a.txt");///WEB-INF目录下
        String realPath2 = servletContext.getRealPath("/WEB-INF/classes/a.txt"); //src目录下
        System.out.println(realPath);

        String filename = req.getParameter("filename");
        String mimeType = servletContext.getMimeType(filename);
        resp.setContentType(mimeType);
        resp.setHeader("content-disposition","attachment;filename="+filename);

        String path = servletContext.getRealPath("/img/我.jpg" );
        FileInputStream fis=new FileInputStream(path);
        ServletOutputStream out = resp.getOutputStream();
        byte[] buff = new byte[1024];
        int len=0;
        while ((len=fis.read(buff))!=-1){
            out.write(buff,0,len);
        }
        fis.close();


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
