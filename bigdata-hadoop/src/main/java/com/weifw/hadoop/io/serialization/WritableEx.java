package com.weifw.hadoop.io.serialization;

import com.google.common.primitives.Bytes;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.DataInputBuffer;
import org.apache.hadoop.io.DataOutputBuffer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WritableEx {

    public static final Logger LOG = LoggerFactory.getLogger(WritableEx.class);

    private final Configuration conf;

    public WritableEx() {
        this.conf = new Configuration();
    }

    public void textWritable() throws Exception {

        String str = "hello world";
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        final DataInputBuffer inputBuffer = new DataInputBuffer();
        Text text = new Text();
        //
        inputBuffer.reset(bytes, bytes.length);

        text.readWithKnownLength(inputBuffer, 3);

        LOG.info(text.toString());
    }

    public void readWriteOperation(String str) {

        byte[] inputBytes = str.getBytes(StandardCharsets.UTF_8);
        inputBytes = Bytes.concat(new byte[] {(byte)22}, inputBytes);
        DataInputBuffer in = new DataInputBuffer();
        DataOutputBuffer out = new DataOutputBuffer();

        Text text = new Text(str);

        try {
            in.reset(inputBytes, inputBytes.length);
            text.readFields(in);
        } catch (Exception ex) {
            LOG.error("testReadFields error !!!");
        }
        try {
            text.write(out);
        } catch (IOException ignored) {

        } catch (Exception ex) {
            LOG.error("testReadWriteOperations error !!!");
        }


    }
}
