package com.weifw.hadoop.io.serialization;

import org.apache.hadoop.io.DataInputBuffer;
import org.apache.hadoop.io.DataOutputBuffer;
import org.apache.hadoop.io.WritableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConcurrentEncodeDecodeThread extends Thread {
    public static final Logger LOG = LoggerFactory.getLogger(ConcurrentEncodeDecodeThread.class);

    public ConcurrentEncodeDecodeThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        final String name = this.getName();
        DataOutputBuffer out = new DataOutputBuffer();
        DataInputBuffer in = new DataInputBuffer();
        for (int i = 0; i < 10; ++i) {
            try {
                out.reset();
                WritableUtils.writeString(out, name);

                in.reset(out.getData(), out.getLength());
                String s = WritableUtils.readString(in);

                LOG.info(Thread.currentThread().getName() + " - " + "input buffer reset contents = " + name + ", " + name + ", " + s);
            } catch (Exception ioe) {
                throw new RuntimeException(ioe);
            }
        }
    }
}
