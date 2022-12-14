package com.weifw.hadoop.io.serialization;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Random;

public class MyWritable implements Writable {

    public static final Random RANDOM = new Random();

    int state = RANDOM.nextInt();

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(state);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.state = dataInput.readInt();
    }

    public static MyWritable read(DataInput dataInput) throws IOException {
        MyWritable myWritable = new MyWritable();
        myWritable.readFields(dataInput);
        return myWritable;
    }

    /**
     * Required by test code, below.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MyWritable))
            return false;
        MyWritable other = (MyWritable) o;
        return this.state == other.state;
    }
}
