package edu.hit.version3.server;

import edu.hit.version3.common.service.BlogService;
import edu.hit.version3.common.service.UserService;
import edu.hit.version3.server.serverImpl.PoolRPCServer;
import edu.hit.version3.server.service.impl.BlogServiceImpl;
import edu.hit.version3.server.service.impl.UserServiceImpl;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName App
 * @Description TODO
 * @Date 2025/4/28 14:23
 **/
public class ServerApp {
    public static void main(String[] args) {
        // 1 注册服务
        BlogService blogService = new BlogServiceImpl();
        UserService userService = new UserServiceImpl();
        ServiceProvider.addService(blogService);
        ServiceProvider.addService(userService);

        // 2 启动服务器
        PoolRPCServer poolRPCServer = new PoolRPCServer();
        poolRPCServer.start();

    }
}
