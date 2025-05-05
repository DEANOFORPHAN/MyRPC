package edu.hit.version4.backUpNIO.nettyQuickStart.version1.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName MyServer
 * @Description TODO
 * @Date 2025/4/29 16:48
 **/
public class MyServer {
    public static void main(String[] args) {
        // 1 创建两个线程组
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 2 创建服务端启动对象参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 2.1 把两个线程组绑定进去， 一个是负责接受客户端的连接， 一个是负责处理与客户端的数据通讯
            bootstrap.group(bossGroup, workerGroup)
                    // 2.2 设置服务端通道实现类
                    .channel(NioServerSocketChannel.class)
                    // 2.3 设置线程队列得到连接个数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 2.4 设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 2.5 初始化通道对象
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            // 给pipeLine管道设置处理器
                            channel.pipeline().addLast(new MyServerHandler());
                        }
                    });
            System.out.println("Netty服务器1.0的服务器启动成功");
            // 3 绑定port然后启动服务端
            ChannelFuture channel = bootstrap.bind(6666).sync();

            // 4 等待服务端关闭
            channel.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
