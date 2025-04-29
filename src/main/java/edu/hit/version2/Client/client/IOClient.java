package edu.hit.version2.Client.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.hit.version2.common.Request;
import edu.hit.version2.common.Response;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName IOClient
 * @Description TODO
 * @Date 2025/4/28 12:15
 **/
public class IOClient {

    public static Response send(String host, int port, Request request) throws IOException, ClassNotFoundException {
        // 1 链接服务器
        Socket socket = new Socket(host, port);

        // 2 获取对应的流
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        // 3 发送数据
        objectOutputStream.writeObject(request);
        objectOutputStream.flush();

        // 4 接收数据
        Response response = (Response) objectInputStream.readObject();

        return response;
    }

}
