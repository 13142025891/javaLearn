package com.wp.springioc;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 11:16 2020/11/1
 */
public class SpringIocLearn {
    public static void main(String[] args) {
        //初始化容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        context.getBeanFactory();
        ShowBeans(context);
      /*  GenericApplicationContext context1=new GenericApplicationContext();
        new XmlBeanDefinitionReader(context1).loadBeanDefinitions("services.xml", "daos.xml");
        context1.refresh();*/


        //PetStoreService service = context.getBean("petStore", PetStoreService.class);

    }

    public static void ShowBeans(ApplicationContext context){
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
    }
}
