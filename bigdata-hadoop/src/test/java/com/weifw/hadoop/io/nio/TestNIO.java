package com.weifw.hadoop.io.nio;

import org.junit.Test;

public class TestNIO {

    @Test
    public void testBIOServer() {
        final BIOServer bio = new BIOServer();
        bio.executeBIO();
    }

    @Test
    public void testBIOClient() {
        final BIOClient bioClient = new BIOClient();
        bioClient.send("hello");
    }

}
