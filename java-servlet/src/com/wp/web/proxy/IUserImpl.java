package com.wp.web.proxy;


import javax.sound.midi.Soundbank;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: WuPna
 * @Description:
 * @Date: Create in 22:11 2020/7/2
 */
public class IUserImpl implements IUser {
    @Override
    public void show() {
        System.out.println("i show");
    }
    @Override
    public void jump(Integer hight) {
        System.out.println("i jump "+hight);
    }
    public static void main(String[] args) {
        /**
         *  参数：
         *      1.类加载器：真实对象.getClass().getClassLoader()
         *      2.接口数组：真实对象.getClass().getInterfaces()
         *      3.处理器：  new InvocationHandler()
         */
        IUser p = (IUser)Proxy.newProxyInstance(IUserImpl.class.getClassLoader(), IUserImpl.class.getInterfaces(), new InvocationHandler() {
            /**
             * 代理对象调用任何方法都会触发
             *  参数：
             *      proxy:代理对象
             *      method：代理对象调用的方法，被封装的对象
             *      args：代理对象调用方法时传递的参数
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName());
                System.out.println(args!=null?args[0]:null);
                return  null;
            }
        });
        IUserImpl u=new IUserImpl();
        IUser p1= (IUser)Proxy.newProxyInstance(IUserImpl.class.getClassLoader(), IUserImpl.class.getInterfaces(), (Object proxy, Method method, Object[] args1)->{
            System.out.println("调用前");
            Integer high=(Integer)args1[0];
            high+=30;
            Object o=method.invoke(u,high);
            System.out.println("调用后");
            return  o;
        });
       p1.jump(3);
       p1.show();

    }
}
