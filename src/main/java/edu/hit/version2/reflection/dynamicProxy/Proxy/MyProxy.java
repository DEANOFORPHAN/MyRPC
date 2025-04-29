package edu.hit.version2.reflection.dynamicProxy.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName MyProxy
 * @Description TODO
 * @Date 2025/4/28 10:29
 **/
public class MyProxy implements InvocationHandler {

    // 1 目标类, 用于反射调用对应的方法
    private Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("调用方法" + method.getName() + "开始");
        method.invoke(target,args);
        long end = System.currentTimeMillis();
        System.out.println("调用方法" + method.getName() + "结束, 耗时" + (end - start) + "ms");

        return null;
    }

    // 2 获取代理类
    /**
     * @param1 loader 类加载器
     * @param2 interfaces 目标类实现的接口
     * @param3 handler 代理类关联的handler, 其中的invoke方法就是代理类的额外逻辑
     * */
    public Object getInstance(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
    }

}
