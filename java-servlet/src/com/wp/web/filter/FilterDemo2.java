package com.wp.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 20:50 2020/7/2
 */
@WebFilter(filterName = "FilterDemo2",value = "/*")
public class FilterDemo2 implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        System.out.println("FilterDemo2 begin");

        chain.doFilter(req, resp);

        System.out.println("FilterDemo2 end");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
