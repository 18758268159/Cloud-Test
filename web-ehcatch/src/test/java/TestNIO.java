import com.ethan.web.webmvc.service.RedisService;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestNIO {

//    public static void main(String[] args) {
//        testNIO();
//    }

    /**
     * (缓冲区)buffer 用于NIO存储数据 支持多种不同的数据类型 <br>
     * 1.byteBuffer <br>
     * 2.charBuffer <br>
     * 3.shortBuffer<br>
     * 4.IntBuffer<br>
     * 5.LongBuffer<br>
     * 6.FloatBuffer <br>
     * 7.DubooBuffer <br>
     * 上述缓冲区管理的方式 几乎<br>
     * 通过allocate（） 获取缓冲区 <br>
     * 二、缓冲区核心的方法 put 存入数据到缓冲区 get <br> 获取缓冲区数据 flip 开启读模式
     * 三、缓冲区四个核心属性<br>
     * capacity:缓冲区最大容量，一旦声明不能改变。 limit:界面(缓冲区可以操作的数据大小) limit后面的数据不能读写。
     * position:缓冲区正在操作的位置
     */
    @Test
    public void testNIO() {

        // 1.指定缓冲区大小1024
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println("--------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        // 2.向缓冲区存放5个数据
        buf.put("abcd1".getBytes());
        System.out.println("--------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        // 3.开启读模式
        buf.flip();
        System.out.println("----------开启读模式...----------");
        System.out.println(buf.position());
        System.out.println(buf.capacity());
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes);
        System.out.println(buf.limit());
        System.out.println(new String(bytes, 0, bytes.length));
        System.out.println("----------重复读模式...----------");
        // 4.开启重复读模式
        buf.rewind();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        byte[] bytes2 = new byte[buf.limit()];
        buf.get(bytes2);
        System.out.println(new String(bytes2, 0, bytes2.length));
        // 5.clean 清空缓冲区  数据依然存在,只不过数据被遗忘
        System.out.println("----------清空缓冲区...----------");
        buf.clear();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println((char) buf.get());

    }

    @Test
    public void test2() {
        ByteBuffer buf = ByteBuffer.allocate(1024);
        String str = "abcd1";
        buf.put(str.getBytes());
        // 开启读取模式
        buf.flip();
        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        buf.mark();
        System.out.println("重置恢复到起始位置..");
        System.out.println(new String(dst, 0, 2));
        System.out.println(buf.position());
        buf.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(buf.position());
        buf.reset();
        System.out.println("重置恢复到mark位置..");
        System.out.println(buf.position());
    }

    @Test
    // 使用直接缓冲区完成文件的复制(內存映射文件)
    public void test3() throws IOException {
        Long startTime = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get("1.png"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("2.png"), StandardOpenOption.READ, StandardOpenOption.WRITE,
                StandardOpenOption.CREATE); // 可读可写可创建
        // 映射文件
        MappedByteBuffer inMapperBuff = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapperBuff = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());
        // 直接对缓冲区进行数据读写操作
        byte[] dst = new byte[inMapperBuff.limit()];

        Long endtTime = System.currentTimeMillis();
        System.out.println("总耗时" + (endtTime - startTime));

        inMapperBuff.get(dst);
        outMapperBuff.put(dst);
        outChannel.close();
        inChannel.close();
    }

    @Test
    // 1.利用通道完成文件复制(非直接缓冲区)
    public void test4() throws IOException {
        FileInputStream fis = new FileInputStream("1.png");
        FileOutputStream fos = new FileOutputStream("2.png");
        // ①获取到通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        // ②分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (inChannel.read(buf) != -1) {
            buf.flip();// 切换到读取模式
            outChannel.write(buf);
            buf.clear();// 清空缓冲区
        }
        // 关闭连接
        outChannel.close();
        inChannel.close();
        fos.close();
        fis.close();
    }

    @Test
    // 分散读取与聚集写入
    public void test5() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("test.txt", "rw");
        // 1.获取通道
        FileChannel channel = raf1.getChannel();
        // 2.分配指定大小的指定缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(100);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);
        // 3.分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel.read(bufs);
        for (ByteBuffer byteBuffer : bufs) {
            // 切换为读取模式
            byteBuffer.flip();
        }
        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("------------------分算读取线分割--------------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));
        // 聚集写入
        RandomAccessFile raf2 = new RandomAccessFile("2.txt", "rw");
        FileChannel channel2 = raf2.getChannel();
        channel2.write(bufs);

        raf1.close();
        raf2.close();
    }

    @Test
    // 分散读取与聚集写入
    public void test6() throws IOException {
        // 获取编码器
        Charset cs1 = Charset.forName("GBK");
        // 获取编码器
        CharsetEncoder ce = cs1.newEncoder();
        // 获取解码器
        CharsetDecoder cd = cs1.newDecoder();
        CharBuffer cBuf = CharBuffer.allocate(1024);
        cBuf.put("蚂蚁课堂牛逼!");
        cBuf.flip();
        // 编码
        ByteBuffer bBuf = ce.encode(cBuf);
        for (int i = 0; i < 12; i++) {
            System.out.println(bBuf.get());
        }
        // 解码
        bBuf.flip();
        CharBuffer cBuf2 = cd.decode(bBuf);
        System.out.println(cBuf2.toString());
        System.out.println("-------------------------------------");

        Charset cs2 = Charset.forName("UTF-8");
        bBuf.flip();
        CharBuffer cbeef = cs2.decode(bBuf);
        System.out.println(cbeef.toString());
    }

}

