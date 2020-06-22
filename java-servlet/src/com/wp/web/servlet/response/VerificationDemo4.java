package com.wp.web.servlet.response;

import sun.java2d.loops.DrawLine;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 20:09 2020/6/21
 */
@WebServlet("/re4")
public class VerificationDemo4 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建一个对象，内存图片
        BufferedImage img = new BufferedImage(100, 50, BufferedImage.TYPE_INT_RGB);
        //美化图片
        Graphics g = img.getGraphics();
        g.setColor(Color.pink);
        g.fillRect(0, 0, 100, 50);
        g.setColor(Color.blue);
        g.drawRect(0, 0, 99, 49);
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        for (int i = 1; i <= 4; i++) {
            int index=random.nextInt(str.length());
            char ch=str.charAt(index);
            g.drawString(ch+"",100/5*i ,50/2);
        }
        for (int i = 1; i <= 10; i++) {
            int x1=random.nextInt(100);
            int x2=random.nextInt(100);
            int y1=random.nextInt(50);
            int y2=random.nextInt(50);
            g.setColor(Color.green);
            g.drawLine(x1,y1,x2,y2);
        }
        ImageIO.write(img, "jpg", resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
