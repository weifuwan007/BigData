package com.weifw.hadoop.io.nio.buffer;

import org.junit.Test;

public class TestBuffer {

    @Test
    public void testFloatBuffer() {
        Buffer buffer = new Buffer(10);
        buffer.floatBuffer();
    }

    @Test
    public void testByteBuffer() {
        Buffer buffer = new Buffer(10);
        buffer.byteBuffer();
    }

    @Test
    public void testByteArrayBuffer() {
        Buffer buffer = new Buffer(10);
        buffer.byteArrayBuffer();

    }

    @Test
    public void testCopyFile() {
        Buffer copyFile = new Buffer("/Users/weifuwan/Documents/back/BigData/bigdata-hadoop/src/main/java/com/weifw/hadoop/io/nio/buffer/in.txt", "/Users/weifuwan/Documents/back/BigData/bigdata-hadoop/src/main/java/com/weifw/hadoop/io/nio/buffer/out.txt");
        copyFile.copy();
    }


}
