package com.wp.learnjava.framework;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@WebServlet(urlPatterns = {"/favicon.ico", "/static/*"})
public class FileServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext ctx = req.getServletContext();

        String urlPath = req.getRequestURI().substring(ctx.getContextPath().length());

        String realPath = ctx.getRealPath(urlPath);
        if (realPath == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Path path = Paths.get(realPath);
        if (!path.toFile().isFile()) {
            // 文件不存在:
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String s = Files.probeContentType(path);
        if (s == null) {
            s = "application/octet-stream";
        }
        resp.setContentType(s);

        ServletOutputStream outputStream = resp.getOutputStream();

        try (InputStream in = new BufferedInputStream(new FileInputStream(realPath))) {

            byte[] buff = new byte[8192];

            int off = in.read(buff);
            while (off != -1) {
                outputStream.write(buff, 0, off);
                off = in.read(buff);
            }

        }

        outputStream.flush();

    }
}
