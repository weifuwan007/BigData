package com.weifw.hadoop.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class BIOClient {
    public static final Logger LOG = LoggerFactory.getLogger(BIOClient.class);

    public BIOClient() {

    }

    public void send(String message) {

        try {
            Socket socket = new Socket("localhost", 8088);
            OutputStream os = socket.getOutputStream();
            LOG.info("客户端发送: " + message);
            os.write(message.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            LOG.error("发送消息失败!" + e);
        }

    }
}
