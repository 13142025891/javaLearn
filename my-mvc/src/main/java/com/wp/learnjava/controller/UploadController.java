package com.wp.learnjava.controller;

import com.wp.learnjava.been.User;
import com.wp.learnjava.framework.GetMapping;
import com.wp.learnjava.framework.ModelAndView;
import com.wp.learnjava.framework.PostMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 8:12 2021/7/12
 */
public class UploadController {
    @PostMapping("/up")
    public ModelAndView index(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        InputStream inputStream = req.getInputStream();
        ByteArrayOutputStream out=new ByteArrayOutputStream();

        byte[] buff=new byte[1024];
        for(;;){
            int len=inputStream.read(buff);
            if(len==-1){
                break;
            }
            out.write(buff,0,len);
        }

        String test=out.toString(String.valueOf(StandardCharsets.UTF_8));

        PrintWriter pw = resp.getWriter();

        pw.write("<h1>Uploaded:</h1>");
        pw.write("<pre><code>");
        pw.write(test);
        pw.write("</code></pre>");
        pw.flush();
        return null;

    }

}
