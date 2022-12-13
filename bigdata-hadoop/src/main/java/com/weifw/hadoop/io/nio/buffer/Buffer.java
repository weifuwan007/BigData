package com.weifw.hadoop.io.nio.buffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.channels.FileChannel;

/**
 * java nio buffer
 */
public class Buffer {

    public static final Logger LOG = LoggerFactory.getLogger(Buffer.class);

    private int capacity;
    private String inFile;
    private String outFile;

    public Buffer(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    public void floatBuffer() {
        // 创建FloatBuffer
        FloatBuffer floatBuffer = FloatBuffer.allocate(capacity);

        for (int i = 0; i < floatBuffer.capacity(); i++) {
            float f = (float) ((float) i * Math.PI);
            floatBuffer.put(f);
        }

        floatBuffer.flip(); // position = 0

        while (floatBuffer.hasRemaining()) {
            float res = floatBuffer.get(); // position++
            LOG.info("res -> " + res);
        }
    }

    public void byteBuffer() {
        // 创建ByteBuffer
        ByteBuffer floatBuffer = ByteBuffer.allocate(capacity);

        for (int i = 0; i < floatBuffer.capacity(); i++) {
            byte f = (byte) (i % 8);
            floatBuffer.put(f); // position ++
        }

        floatBuffer.flip(); // position = 0

        while (floatBuffer.hasRemaining()) {
            byte res = floatBuffer.get(); // position++
            LOG.info("res -> " + res);
        }
    }

    public void byteArrayBuffer() {
        byte[] bytes = new byte[capacity];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

        for (int i = 0; i < byteBuffer.capacity(); i++) {
            byteBuffer.put((byte) ((byte) 'a' + i)); // position ++
        }

        byteBuffer.flip(); // position = 0

        for (int j = 0; j < byteBuffer.capacity(); j++) {
            LOG.info(byteBuffer.get() + ""); // position --
        }
    }

    public void copy() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(inFile);
            fileOutputStream = new FileOutputStream(outFile);
        } catch (FileNotFoundException e) {
            LOG.error("文件没有找到 " + e);
        }

        FileChannel inChannel = null;
        FileChannel outChannel = null;
        if (fileInputStream != null && fileOutputStream != null) {
            inChannel = fileInputStream.getChannel();
            outChannel = fileOutputStream.getChannel();
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true) {
            // 先清空bytebuffer, 一次读取1024，然后写1024
            byteBuffer.clear();

            try {
                assert inChannel != null;
                int read = inChannel.read(byteBuffer); // position ++
                if (read == -1) {
                    break;
                }

                byteBuffer.flip(); // position = 0

                outChannel.write(byteBuffer); // position ++

            } catch (IOException e) {
                LOG.info("读取文件失败", e);
            }
        }
    }
}
