package com.weifw.hadoop.io.serialization;

import org.junit.Test;

public class TestWritable {

    @Test
    public void testTextWritable() throws Exception {
        WritableEx writableEx = new WritableEx();
        writableEx.textWritable();
    }

    @Test
    public void testReadWriteOperation() {
        WritableEx writableEx = new WritableEx();
        writableEx.readWriteOperation("adsawseeeeegqewgasddga");
    }

    @Test
    public void testConcurrentIO() {
        ConcurrentEncodeDecodeThread t1 = new ConcurrentEncodeDecodeThread("hadoop");
        ConcurrentEncodeDecodeThread t2 = new ConcurrentEncodeDecodeThread("apache");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.err.println(e);
        }


    }

}
