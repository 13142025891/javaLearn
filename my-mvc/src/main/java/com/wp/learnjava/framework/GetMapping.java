package com.wp.learnjava.framework;

import java.lang.annotation.*;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 8:14 2021/7/12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface GetMapping {
    String value();
}
