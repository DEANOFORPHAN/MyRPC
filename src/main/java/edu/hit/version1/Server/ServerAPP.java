package edu.hit.version1.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import edu.hit.version1.Server.po.User;
import edu.hit.version1.Server.po.service.UserService;
import edu.hit.version1.Server.po.service.impl.UserServiceImpl;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName ServerAPP
 * @Description TODO
 * @Date 2025/4/27 20:13
 **/
public class ServerAPP {
    public static void main(String[] args)   {
        try {
            // 1 监听8899端口
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("服务端启动了");

            UserService userService = new UserServiceImpl();

            // 2 接受客户端的连接
            while (true) {
                // 2.1 阻塞BIO等待新的连接
                System.out.println("等待新的客户端连接...");
                Socket communicateSocker = serverSocket.accept();
                System.out.println("有新的客户端连接了");
                // 创建一个新的线程用于接受参数， 同时返回用户
                new Thread(() -> {
                    // 1 读取数字
                    try {
                        ObjectInputStream objectInputStream = new ObjectInputStream(communicateSocker.getInputStream());
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(communicateSocker.getOutputStream());
                        int uid = objectInputStream.readInt();

                        System.out.println("服务端接收到了一个请求, 用户id为: " + uid);
                        User user = userService.getUserById(uid);
                        System.out.println("服务端返回了一个用户, 用户为: " + user);
                        objectOutputStream.writeObject(user);
                        objectOutputStream.flush();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
