package com.weifw.hadoop.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO
 */
public class BIOServer {

    public static final Logger LOG = LoggerFactory.getLogger(BIOServer.class);

    public BIOServer() {

    }

    public void executeBIO() {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(8088);
            LOG.info("服务器启动,等待连接。。。。。");
            while (!Thread.currentThread().isInterrupted()) {
                final Socket accept = serverSocket.accept();
                executorService.submit(new ConnectIOHandler(accept));
            }
        } catch (IOException e) {
            LOG.error("socket server start fail !", e);
        }


    }

}

class ConnectIOHandler extends Thread {

    public static final Logger LOG = LoggerFactory.getLogger(ConnectIOHandler.class);

    private final Socket socket;

    public ConnectIOHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) {
            try {
                //从套接字中获取输入流
                InputStream is = socket.getInputStream();
                //读取数据
                byte[] b = new byte[1024];
                int len = is.read(b);
                LOG.info("client：" + new String(b, 0, len));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
