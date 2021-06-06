package com.wp.basic.reflectionL;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Basic {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        System.out.println(String.class);

        String s="Hello";
        System.out.println(s.getClass());

        Class cls=Class.forName("java.lang.String");
        System.out.println(cls);

        Field f = String.class.getDeclaredField("value");
        f.getName(); // "value"
        f.getType(); // class [B 表示byte[]类型
        int m = f.getModifiers();
        System.out.println(Modifier.isFinal(m));; // true
        System.out.println(Modifier.isPublic(m));; // false
        System.out.println(Modifier.isProtected(m));; // false
        System.out.println(Modifier.isPrivate(m));; // true
        System.out.println(Modifier.isStatic(m));; // false


        Person p=new Person("wupan");

        Class c=p.getClass();
        Field ff=c.getDeclaredField("name");
        ff.setAccessible(true);
        System.out.println(ff.get(p));




    }


}
class Person{
    private String name;

    public Person(String name) {
        this.name = name;
    }

}