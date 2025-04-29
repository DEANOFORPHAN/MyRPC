package edu.hit.version4.backUpNIO.BufferAndChanel.channerl;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author xufeixiang <xufeixiang03@kuaishou.com>
 * @ClassName app
 * @Description TODO
 * @Date 2025/4/29 11:01
 **/
public class app {

    // 这个Demo主药就是创建两个Path
    // 然后分别创建两个FileChannel建立和两个 数据源 的连接
    // 然后使用transferTo来传输， 这个方法会直接将数据从一个通道传输到另一个通道， 不需要经过内存， 所以速度会快很多
    // 最后关闭两个通道
    public static void main(String[] args) throws IOException {
        // 1 创建两个Path
        Path pathSrc = Paths.get("src/main/resources/fileChannelTestSrc.txt");
        // 这里一定需要有目标文件， 否则需要先check， 然后再创建
        Path pathDst = Paths.get("src/main/resources/fileChannelTestDst111.txt");


        // 2 try with resource来创建两个Channel
        try (FileChannel source = FileChannel.open(pathSrc, StandardOpenOption.READ);
                FileChannel dst = FileChannel.open(pathDst, StandardOpenOption.WRITE, StandardOpenOption.READ)){
            // 2.1 不断循环读， 每次更新一下当前总共读了多少数据
            // 下一次需要读取的位置
            long position = 0;
            // 总长度
            long size = source.size();

            // 2.2 开始循环读
            while (position < size) {
                long transSize = source.transferTo(position, size - position, dst);
                // 更新进度
                position += transSize;
            }
        }
    }

}
