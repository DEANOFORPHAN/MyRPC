package edu.hit.version4.backUpNIO.BufferAndChanel.buffer;

import java.nio.ByteBuffer;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName App
 * @Description TODO
 * @Date 2025/4/29 10:51
 **/
public class App {
    public static void main(String[] args) {
        // 这里简单使用一下Buffer， 他的子类是ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 1 放入数据， 这里实际上是Byte数组， 所以直接放入大小合适的Byte数组即可

        System.out.println("此时所有位置信息如下" + buffer.mark());
        System.out.println("----------------------");
        String str = "手写RPC中";
        buffer.put(str.getBytes());

        System.out.println("此时所有位置信息如下" + buffer.mark());

        // 2 读取数据到另外一个byte[]中

        // 2.1 首先需要flip， 重制position读位置和limit最终长度
        buffer.flip();
        System.out.println("flip反转后--------------------");
        System.out.println("此时所有位置信息如下" + buffer.mark());
        byte[] dst = new byte[buffer.limit()];
        // 这个方法相当于直接把buffer中dst长度的内容拷贝进来， 但是如果放在flip之前就会导致需要放入1024长度， 导致报错
        buffer.get(dst);
        System.out.println(new String(dst, 0, dst.length));

    }
}
