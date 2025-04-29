package edu.hit.version3.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

import edu.hit.version3.common.params.Request;
import edu.hit.version3.common.params.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName RPCHandler
 * @Description TODO
 * @Date 2025/4/28 17:06
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RPCHandler implements Runnable{

    private Socket socket;

    @Override
    public void run() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            // 1 拿到Request
            Request request = (Request) inputStream.readObject();
            // 2 处理对应的RPC调用
            Object response = handleRequest(request);
            // 3 写回数据
            outputStream.writeObject(response);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object handleRequest(Request request) throws Exception {
        // 1 提取出接口
        String interfaceName = request.getInterfaceName();
        // 2 提取出服务
        Object service = ServiceProvider.getService(interfaceName);
        // 3 拿到方法Name， 参数， 参数类型
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();
        // 4 抽出方法， 然后开始反射
        Method method = service.getClass().getMethod(methodName, parameterTypes);
        Object result = method.invoke(service, parameters);
        // 5 封装最后的结果并返回
        return Response.success(result);
    }
}
