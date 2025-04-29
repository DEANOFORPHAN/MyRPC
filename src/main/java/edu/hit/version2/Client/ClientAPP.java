package edu.hit.version2.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.UUID;


import edu.hit.version2.Client.proxy.ClientProxy;
import edu.hit.version2.common.po.User;
import edu.hit.version2.common.service.UserService;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName ClientAPP
 * @Description TODO
 * @Date 2025/4/27 20:13
 **/
public class ClientAPP {
    public static void main(String[] args) throws IOException {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        UserService instance = (UserService) clientProxy.getInstance(UserService.class);

        User user = instance.getUser(1);
        System.out.println(user);

        instance.addUser(User.builder()
                .id(1)
                .build());


    }
}
