package edu.hit.version4.backUpNIO.nettyQuickStart.version1.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName MyClient
 * @Description TODO
 * @Date 2025/4/29 17:07
 **/
public class MyClient {
    public static void main(String[] args) {
        // 1 客户端只需要一个线程组
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();

        try {
            // 2 配置客户端启动参数
            Bootstrap bootstrap = new Bootstrap();
            // 2.1 配置客户端线程组
            bootstrap.group(eventExecutors)
                    // 2.2 配置客户端的通道实现类型
                    .channel(NioSocketChannel.class)

                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new MyClientHandler());
                        }
                    });

            System.out.println("Netty客户端1.0的服务器启动成功");
            // 3 连接服务器
            ChannelFuture channel = bootstrap.connect("127.0.0.1", 6666).sync();
            // 4 等待服务端关闭
            channel.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
