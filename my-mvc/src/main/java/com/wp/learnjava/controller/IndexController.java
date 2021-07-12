package com.wp.learnjava.controller;

import com.wp.learnjava.been.User;
import com.wp.learnjava.framework.GetMapping;
import com.wp.learnjava.framework.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 8:12 2021/7/12
 */
public class IndexController {
    @GetMapping("/")
    public ModelAndView index(HttpSession session) {
        User user = (User) session.getAttribute("user");
        return new ModelAndView("/index.html", "user", user);
    }

    @GetMapping("/hello")
    public ModelAndView hello(String name) {
        if (name == null) {
            name = "World";
        }
        return new ModelAndView("/hello.html", "name", name);
    }
}
