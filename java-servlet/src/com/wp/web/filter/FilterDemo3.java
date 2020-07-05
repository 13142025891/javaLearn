package com.wp.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 20:50 2020/7/2
 */
@WebFilter(filterName = "FilterDemo3 ",value = "/*",dispatcherTypes = DispatcherType.ASYNC)
public class FilterDemo3 implements Filter {
    //服务器关闭后调用
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
    //每一次请求被拦截时执行
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("doFilter");
        chain.doFilter(req, resp);
    }
    //服务器启动后，会创建filter，然后调用init
    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("init");
    }

}
