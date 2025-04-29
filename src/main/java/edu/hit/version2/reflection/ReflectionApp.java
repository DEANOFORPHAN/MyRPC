package edu.hit.version2.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName ReflectionApp
 * @Description TODO
 * @Date 2025/4/28 09:47
 **/
public class ReflectionApp {
    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException {
        // 1 获取 class对象
        Class<?> aClass = Class.forName("edu.hit.version2.Client.po.User");

        // 2 初始化对象两种方法
            // 2.1 不再推荐使用
        // Object o = aClass.newInstance();
            // 2.2 首先获取 构造方法， 然后实例化
        Constructor constructor = aClass.getConstructor();
        Object object = constructor.newInstance();

        // 3 反射调用
            // 3.1 获取方法
        Method setName = aClass.getMethod("setName", String.class);
        Method getName = aClass.getMethod("getName");

        setName.invoke(object, "xfx");
        System.out.println(getName.invoke(object));

    }
}
