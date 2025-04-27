package edu.hit.version1.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import edu.hit.version1.Server.po.User;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName ClientAPP
 * @Description TODO
 * @Date 2025/4/27 20:13
 **/
public class ClientAPP {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            // 1 创建一个Socket对象， 指定链接本机8899
            Socket socket = new Socket("127.0.0.1", 8899);

            // 2 从Socker中获取输入输出流
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            // 3 发送uid
            objectOutputStream.writeInt(new Random().nextInt(100));

            objectOutputStream.flush();
            // System.out.println("客户端发送了一个请求");
            // 4 接受数据
            User user = (User) objectInputStream.readObject();
            System.out.println("服务端返回的用户为: " + user);
        } catch (Exception e) {
            System.out.println("客户端异常: " + e.getMessage());
        } finally {
            objectOutputStream.close();
            objectInputStream.close();
        }

    }
}
