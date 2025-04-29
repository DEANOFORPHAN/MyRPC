package edu.hit.version2.reflection.dynamicProxy;


import edu.hit.version2.reflection.dynamicProxy.Proxy.MyProxy;
import edu.hit.version2.reflection.dynamicProxy.po.UserService;
import edu.hit.version2.reflection.dynamicProxy.po.impl.UserServiceImpl;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName App
 * @Description TODO
 * @Date 2025/4/28 10:28
 **/
public class App {
    public static void main(String[] args) {
        // 1 创建目标对象

        UserService userService = new UserServiceImpl();
        MyProxy myProxy = new MyProxy();
        // 2 传入代理对象, 这里一定需要用对应的接口类型来接受
        UserService instance = (UserService) myProxy.getInstance(userService);

        // 3 方法调用
        instance.setId(1);
        instance.doSomething();
    }
}
