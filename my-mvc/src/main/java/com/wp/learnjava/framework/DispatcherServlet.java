package com.wp.learnjava.framework;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wp.learnjava.controller.IndexController;
import com.wp.learnjava.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 6:57 2021/7/12
 */

public class DispatcherServlet extends HttpServlet {

    private final Logger logger= LoggerFactory.getLogger(getClass());

    private Map<String, GetDispatcher> getMappings = new HashMap<>();

    private Map<String, PostDispatcher> postMappings = new HashMap<>();

    // TODO: 可指定package并自动扫描:
    private List<Class<?>> controllers =Arrays.asList(IndexController.class, UserController.class);

    private ViewEngine viewEngine;
    @Override
    public void init() throws ServletException {
        logger.info("init {}...", getClass().getSimpleName());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 依次处理每个Controller:
        for (Class<?> controllerClass : controllers) {
            try {
                Object controllerInstance = controllerClass.getConstructor().newInstance();
                // 依次处理每个Method:
                for (Method method : controllerClass.getMethods()) {
                    if (method.getAnnotation(GetMapping.class) != null) {
                        // 处理@Get:
                        if (method.getReturnType() != ModelAndView.class && method.getReturnType() != void.class) {
                            throw new UnsupportedOperationException(
                                    "Unsupported return type: " + method.getReturnType() + " for method: " + method);
                        }
                        for (Class<?> parameterClass : method.getParameterTypes()) {
                            if (!supportedGetParameterTypes.contains(parameterClass)) {
                                throw new UnsupportedOperationException(
                                        "Unsupported parameter type: " + parameterClass + " for method: " + method);
                            }
                        }
                        String[] parameterNames = Arrays.stream(method.getParameters()).map(p -> p.getName())
                                .toArray(String[]::new);
                        String path = method.getAnnotation(GetMapping.class).value();
                        logger.info("Found GET: {} => {}", path, method);
                        this.getMappings.put(path, new GetDispatcher(controllerInstance, method, parameterNames,
                                method.getParameterTypes()));
                    } else if (method.getAnnotation(PostMapping.class) != null) {
                        // 处理@Post:
                        if (method.getReturnType() != ModelAndView.class && method.getReturnType() != void.class) {
                            throw new UnsupportedOperationException(
                                    "Unsupported return type: " + method.getReturnType() + " for method: " + method);
                        }
                        Class<?> requestBodyClass = null;
                        for (Class<?> parameterClass : method.getParameterTypes()) {
                            if (!supportedPostParameterTypes.contains(parameterClass)) {
                                if (requestBodyClass == null) {
                                    requestBodyClass = parameterClass;
                                } else {
                                    throw new UnsupportedOperationException("Unsupported duplicate request body type: "
                                            + parameterClass + " for method: " + method);
                                }
                            }
                        }
                        String path = method.getAnnotation(PostMapping.class).value();
                        logger.info("Found POST: {} => {}", path, method);
                        this.postMappings.put(path, new PostDispatcher(controllerInstance, method,
                                method.getParameterTypes(), objectMapper));
                    }
                }
            } catch (ReflectiveOperationException e) {
                throw new ServletException(e);
            }
        }
        // 创建ViewEngine:
        this.viewEngine = new ViewEngine(getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp, this.getMappings);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp, this.postMappings);
    }
    private void process(HttpServletRequest req, HttpServletResponse resp,
                         Map<String, ? extends AbstractDispatcher> dispatcherMap) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String path = req.getRequestURI().substring(req.getContextPath().length());
        AbstractDispatcher dispatcher = dispatcherMap.get(path);
        if (dispatcher == null) {
            resp.sendError(404);
            return;
        }
        ModelAndView mv = null;
        try {
            mv = dispatcher.invoke(req, resp);
        } catch (ReflectiveOperationException e) {
            throw new ServletException(e);
        }
        if (mv == null) {
            return;
        }
        if (mv.view.startsWith("redirect:")) {
            resp.sendRedirect(mv.view.substring(9));
            return;
        }
        PrintWriter pw = resp.getWriter();
        this.viewEngine.render(mv, pw);
        pw.flush();
    }

    private static final Set<Class<?>> supportedGetParameterTypes = Stream.of(int.class, long.class, boolean.class,
            String.class, HttpServletRequest.class, HttpServletResponse.class, HttpSession.class).collect(Collectors.toSet());


    private static final Set<Class<?>> supportedPostParameterTypes = Stream.of(HttpServletRequest.class,
            HttpServletResponse.class, HttpSession.class).collect(Collectors.toSet());


}

class GetDispatcher extends AbstractDispatcher {
    @Override
    public ModelAndView invoke(HttpServletRequest request, HttpServletResponse response) throws IOException, ReflectiveOperationException {
        return null;
    }


}

class PostDispatcher extends AbstractDispatcher{
    @Override
    public ModelAndView invoke(HttpServletRequest request, HttpServletResponse response) throws IOException, ReflectiveOperationException {
        return null;
    }
}
abstract class AbstractDispatcher {

    public abstract ModelAndView invoke(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ReflectiveOperationException;
}

class ModelAndView {
    Map<String, Object> model;
    String view;
}