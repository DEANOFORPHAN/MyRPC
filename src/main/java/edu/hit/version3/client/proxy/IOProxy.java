package edu.hit.version3.client.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.hit.version3.common.params.Request;
import edu.hit.version3.common.params.Response;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName IOProxy
 * @Description TODO
 * @Date 2025/4/28 17:40
 **/
public class IOProxy {

    public static Object send(String host, int port, Request request) throws Exception {
        // 1 连接Socket
        Socket socket = new Socket(host, port);

        // 2 获取流
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

        // 3 发送请求
        oos.writeObject(request);
        oos.flush();

        // 4 接受Response
        Response o = (Response) ois.readObject();

        // 5 拿出数据并返回
        return o.getData();
    }
}
