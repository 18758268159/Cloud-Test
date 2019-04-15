import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class SocketTest {

    @Test
    public void SocketServer() throws IOException {
        System.out.println("socket tcp服务器端启动....");
        ServerSocket serverSocket = null;
        // 等待客户端请求
        try {
            serverSocket = new ServerSocket(8080);
            while (true) {
                Socket accept = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            InputStream inputStream = accept.getInputStream();
                            // 转换成string类型
                            byte[] buf = new byte[1024];
                            int len = inputStream.read(buf);
                            String str = new String(buf, 0, len);
                            System.out.println("服务器接受客户端内容:" + str);
                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }
    }

    @Test
    public void TcpClient() throws IOException {
        System.out.println("socket tcp 客户端启动....");
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("我是蚂蚁课堂".getBytes());
        socket.close();
    }


    // NIO测试
    public static void main(String[] args) throws IOException {
        NIOClient();
    }

    public static void NIOClient() throws IOException {
        System.out.println("客户端已经启动....");
        // 1.创建通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
        // 2.切换异步非阻塞
        sChannel.configureBlocking(false); // jdk1.7才有
        // 3.指定缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入内容。。\n");
        while (scanner.hasNext()) {
            System.out.println("请输入内容。。\n");
            String str = scanner.next();
            byteBuffer.put((new Date().toString() + "\n" + str).getBytes());
            // 4.切换读取模式
            byteBuffer.flip();
            sChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        sChannel.close();
    }

    @Test
    public void NIOServer() throws IOException {
        System.out.println("服务器端已经启动....");
        // 1.创建通道
        ServerSocketChannel sChannel = ServerSocketChannel.open();
        // 2.切换读取模式
        sChannel.configureBlocking(false);
        // 3.绑定连接
        sChannel.bind(new InetSocketAddress(8080));
        // 4.获取选择器
        Selector selector = Selector.open();
        // 5.将通道注册到选择器 "并且指定监听接受事件"
        sChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 6. 轮训式 获取选择 "已经准备就绪"的事件
        while (selector.select() > 0) {
            // 7.获取当前选择器所有注册的"选择键(已经就绪的监听事件)"
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                // 8.获取准备就绪的事件
                SelectionKey sk = it.next();
                // 9.判断具体是什么事件准备就绪
                if (sk.isAcceptable()) {
                    // 10.若"接受就绪",获取客户端连接
                    SocketChannel socketChannel = sChannel.accept();
                    // 11.设置阻塞模式
                    socketChannel.configureBlocking(false);
                    // 12.将该通道注册到服务器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    // 13.获取当前选择器"就绪" 状态的通道
                    SocketChannel socketChannel = (SocketChannel) sk.channel();
                    // 14.读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len = 0;
                    while ((len = socketChannel.read(buf)) > 0) {
                        buf.flip();
                        String aaa = new String(buf.array());
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                it.remove();
            }
        }
    }

}
