package com.wp.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 20:50 2020/7/2
 */
//@WebFilter(filterName = "FilterDemo1",value = "/*")
public class FilterDemo1 implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println("i am a filter!");
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
