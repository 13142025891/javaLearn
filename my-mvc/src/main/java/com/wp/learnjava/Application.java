package com.wp.learnjava;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.security.DigestInputStream;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 6:56 2021/7/12
 */
public class Application {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat=new Tomcat();
        tomcat.setPort(Integer.getInteger("port",8080));
        tomcat.getConnector();
        Context ctx=tomcat.addWebapp("", new File("./my-mvc/src/main/webapp").getAbsolutePath());
        WebResourceRoot resources=new StandardRoot(ctx);
        resources.addPreResources(
                new DirResourceSet(resources,"/WEB-INF/classes",new File("./my-mvc/target/classes").getAbsolutePath(), "/"));

        ctx.setResources(resources);
        tomcat.start();
        tomcat.getServer().await();
    }
}
