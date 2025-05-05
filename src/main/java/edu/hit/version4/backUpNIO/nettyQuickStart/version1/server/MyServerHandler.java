package edu.hit.version4.backUpNIO.nettyQuickStart.version1.server;

import java.net.SocketAddress;
import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName MyServerHandler
 * @Description TODO
 * @Date 2025/4/29 16:32
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    // 用于处理 读取事件
    // 1 处理 read事件
    // 2 当read事件处理函数结束后的回调事件 (1的回调事件)
    // 3 处理异常发生的事件

    /**
     * @description 这个方法一般用于处理 可读事件,
     * @param ctx 上下文对象 , 可以通过这个对象获取到pipeline 和 channel
     * @param msg 客户端发来的消息
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // msg一般是另一个Netty客户端发来的 ByteBuf
        ByteBuf byteBuf = (ByteBuf) msg;

        // 获取ctx， 从而可以拿到channel， 通过channel可以拿到客户端的信息
        Channel channel = ctx.channel();
        SocketAddress clientAddress = channel.remoteAddress();

        // 打印客户端的信息
        System.out.println("客户端地址：" + clientAddress);
        System.out.println("客户端发送的消息：" + byteBuf.toString(CharsetUtil.UTF_8));

    }

    /**
     * @description 这个方法一般是处理完 读事件后的 回调事件 用于回调或者给客户端进行响应
     * @param ctx 上下文对象 , 可以通过这个对象获取到pipeline 和 channel
     * */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端响应已收到消息， 并给你发送一个问号", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
