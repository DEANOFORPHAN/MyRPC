package edu.hit.version3.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import edu.hit.version3.client.proxy.ClientProxy;
import edu.hit.version3.common.po.Blog;
import edu.hit.version3.common.po.User;
import edu.hit.version3.common.service.BlogService;
import edu.hit.version3.common.service.UserService;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName ClientApp
 * @Description TODO
 * @Date 2025/4/28 14:23
 **/
public class ClientApp {
    public static void main(String[] args) throws Exception {
        ClientProxy clientProxy = new ClientProxy();

        UserService userService = (UserService) clientProxy.getInstance(UserService.class);
        User user = userService.getUserById(1);
        System.out.println(user);

        BlogService blogService = (BlogService) clientProxy.getInstance(BlogService.class);
        Blog blog = blogService.getBlogById(1);
        System.out.println(blog);
    }
}
