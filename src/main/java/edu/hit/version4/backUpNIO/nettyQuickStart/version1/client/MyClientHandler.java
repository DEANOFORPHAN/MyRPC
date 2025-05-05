package edu.hit.version4.backUpNIO.nettyQuickStart.version1.client;

import java.net.SocketAddress;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName MyClientHandler
 * @Description TODO
 * @Date 2025/4/29 17:07
 **/
public class MyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * @description 这个方法在 通道只要是活跃的 就会触发该方法， 在client中就是自动连接上服务器的时候触发 然后发送一段数据
     * @param ctx
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("这里是Client， 正在尝试发消息个Server端!", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 1 拿到通道
        Channel channel = ctx.channel();
        // 2 拿到服务端ip
        SocketAddress serverAddress = channel.remoteAddress();
        // 3 拿到Server回发的数据
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("收到客户端" + serverAddress + "发送来的数据" + byteBuf.toString(CharsetUtil.UTF_8));
    }
}
