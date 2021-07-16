package com.wp.learnjava.filter;


import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebFilter("/up/*")
public class ValidateUploadFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String digest = req.getHeader("Signature-Method");
        String signature = req.getHeader("Signature");
        if (digest == null || digest.isEmpty() || signature.isEmpty() || signature.isEmpty()) {
            sendErrorPage(resp, "Missing signature.");
            return;
        }

        MessageDigest md = getMessageDigest(digest);
        DigestInputStream input = new DigestInputStream(req.getInputStream(), md);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        for (; ; ) {
            int len = input.read(buff);
            if (len == -1) {
                break;
            }
            output.write(buff, 0, len);
        }

        String actual = toHexString(md.digest());
        if(!actual.equals(signature)){
            sendErrorPage(resp,"Invalid signature");
            return;
        }



        filterChain.doFilter(new ReReadableHttpServletRequest(req,output.toByteArray()),servletResponse);

    }

    private String toHexString(byte[] digest) {
        StringBuffer sb = new StringBuffer();

        for (byte b : digest) {
            sb.append(String.format("%02x", b));

        }

        return sb.toString();
    }

    private MessageDigest getMessageDigest(String digest) throws ServletException {
        try {
            return MessageDigest.getInstance(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new ServletException(e);
        }
    }

    private void sendErrorPage(HttpServletResponse resp, String errorMessage) throws IOException {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        PrintWriter pw = resp.getWriter();
        pw.write("<html><body><h1>");
        pw.write(errorMessage);
        pw.write("</h1></body></html>");
        pw.flush();
    }

    @Override
    public void destroy() {

    }


}

class ReReadableHttpServletRequest extends HttpServletRequestWrapper{
    private byte[] body;
    private boolean open=false;

    public ReReadableHttpServletRequest(HttpServletRequest request,byte[] body) {

        super(request);
        this.body=body;

    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if(open){
            throw new IllegalStateException("Cannot re-open input stream!");
        }
        open=true;
        return new ServletInputStream() {

            private int offset = 0;

            @Override
            public boolean isFinished() {
                return offset >= body.length;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                if (offset >= body.length) {
                    return -1;
                }
                int n = body[offset] & 0xff;
                offset++;
                return n;
            }
        };



    }

    // 返回Reader:
    @Override
    public BufferedReader getReader() throws IOException {
        if (open) {
            throw new IllegalStateException("Cannot re-open reader!");
        }
        open = true;
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(body), "UTF-8"));
    }
}