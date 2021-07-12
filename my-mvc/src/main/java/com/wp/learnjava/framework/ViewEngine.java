package com.wp.learnjava.framework;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 8:12 2021/7/12
 */
public class ViewEngine {

    private final PebbleEngine engine;

    public ViewEngine(ServletContext context) {
        // 定义一个ServletLoader用于加载模板:
        ServletLoader loader = new ServletLoader(context);
        // 模板编码:
        loader.setCharset("UTF-8");
        // 模板前缀，这里默认模板必须放在`/WEB-INF/templates`目录:
        loader.setPrefix("/WEB-INF/templates");
        // 模板后缀:
        loader.setSuffix("");
        // 创建Pebble实例:
        this.engine = new PebbleEngine.Builder()
                .autoEscaping(true) // 默认打开HTML字符转义，防止XSS攻击
                .cacheActive(false) // 禁用缓存使得每次修改模板可以立刻看到效果
                .loader(loader).build();
    }

    public void render(ModelAndView mv, PrintWriter pw) throws IOException {
        // 查找模板:
        PebbleTemplate template = this.engine.getTemplate(mv.view);
        // 渲染:
        template.evaluate(pw, mv.model);
    }
}
