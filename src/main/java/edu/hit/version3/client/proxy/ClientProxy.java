package edu.hit.version3.client.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import edu.hit.version3.common.params.Request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName ClientProxy
 * @Description TODO
 * @Date 2025/4/28 17:39
 **/
// @NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClientProxy implements InvocationHandler {
    private String host;
    private int port;

    // 1 默认创建端口和主机
    public ClientProxy() {
        this.host = "127.0.0.1";
        this.port = 8899;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 0 打印一下代理的类名
        System.out.println(proxy.getClass().getName());
        // 1 打印一下代理的接口
        // System.out.println("proxy.getClass().getInterfaces() = " + proxy.getClass().getInterfaces());
        for (Class<?> aClass : proxy.getClass().getInterfaces()) {
            System.out.println(aClass.getName());
        }

        // 1 封装好请求
        Request request = Request.builder()
                .parameters(args)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .interfaceName(method.getDeclaringClass().getName())  //用声明的时候的class类型
                .build()
                ;

        Object response = IOProxy.send(host, port, request);
        return response;
    }


    /**
     * @param clazz 被代理的接口class对象
     * */
    public Object getInstance(Class<?> clazz) {
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }

}
