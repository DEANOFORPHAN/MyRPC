package edu.hit.version2.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import edu.hit.version2.common.Request;
import edu.hit.version2.common.Response;
import edu.hit.version2.common.service.UserService;
import edu.hit.version2.Server.service.UserServiceImpl;


/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName ServerAPP
 * @Description TODO
 * @Date 2025/4/27 20:13
 **/
public class ServerAPP {

    public static void main(String[] args) throws IOException {
        // 1 创建一个ServerSocket监听客户端请求
        ServerSocket serverSocket = new ServerSocket(8899);
        UserService userService = new UserServiceImpl();

        // 2 循环BIO监听数据
        while (true) {
            Socket communicateSocket = serverSocket.accept();
            // 2.1 获取对应的Socket对象流
            ObjectInputStream objectInputStream = new ObjectInputStream(communicateSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(communicateSocket.getOutputStream());

            // 2 new一个线程执行对应的逻辑
            new Thread(() -> {
                try {
                    // 1 获取对应的请求
                    Request request = (Request) objectInputStream.readObject();
                    // 2 反射调用 这里实际上对应的接口已经写死了， 只有一个UserService, 但是可以有多个method可以指定

                    Method method = UserService.class.getMethod(request.getMethodName(), request.getParameterTypes());
                    Object result = method.invoke(userService, request.getParameters());

                    // 3 返回数据
                    // objectOutputStream.writeObject(Response.builder().code(200).data(request).build()); // 这里可以封装一个Response的succes或者fail
                    objectOutputStream.writeObject(Response.success(result));
                    objectOutputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
