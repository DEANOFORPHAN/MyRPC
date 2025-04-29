package edu.hit.version3.server.serverImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import edu.hit.version3.server.RPCHandler;
import edu.hit.version3.server.RPCServer;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName PoolRPCServer
 * @Description Server的线程池实现模式, 使用一个线程池来处理每一个Socker的处理流程
 * @Date 2025/4/28 14:47
 **/
public class PoolRPCServer implements RPCServer {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT;
    private static final int MAX_POOL_SIZE = CPU_COUNT + 1;

    private static ThreadPoolExecutor threadPoolExecutor;

    // 1 默认版本的线程池构造
    public PoolRPCServer() {
        // 1 这里核心直接设置本机最大就好了， 因为没有实际的IO
        // 2 这里最后的阻塞队列一定需要设置上限, 防止OOM
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1000));
    }

    @Override
    public void start() {
        ServerSocket serverSocket = null;
        try {
            // 1 首先还是需要绑定到某一个ServerSocket中
            serverSocket = new ServerSocket(8899);
            while (true) {
                Socket communicateSocket = serverSocket.accept();
                // 2 抽离出Runnable方法， 实现对每一个Socket用户的RPC处理的抽象
                threadPoolExecutor.execute(new RPCHandler(communicateSocket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {

    }
}
