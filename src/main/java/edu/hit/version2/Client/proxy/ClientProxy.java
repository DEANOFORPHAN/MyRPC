package edu.hit.version2.Client.proxy;

import java.io.ObjectStreamException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import edu.hit.version2.Client.client.IOClient;
import edu.hit.version2.common.Request;
import edu.hit.version2.common.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName ClientProxy
 * @Description TODO
 * @Date 2025/4/28 12:17
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientProxy implements InvocationHandler {
    private String host;
    private int port;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1 封装Request
        Request request = Request.builder()
                // .interfaceName(proxy.getClass().getName()) //这里需要使用method.getDeclaringClass().getName()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .parameters(args)
                .build();

        // 2 发送请求
        Response response = IOClient.send(host, port, request);

        // 3 抽出结果进行返回
        return response.getData();
    }

    public Object getInstance(Class<?> clazz) {
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }


}
