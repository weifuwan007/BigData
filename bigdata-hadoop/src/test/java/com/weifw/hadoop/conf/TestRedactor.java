package com.weifw.hadoop.conf;

import org.junit.Test;

public class TestRedactor {

    @Test
    public void testRedactorWithNoCore() {
        ConfRedactor confRedactor = new ConfRedactor(false);
        confRedactor.redactor();
    }

    @Test
    public void testRedactorNoCore() {
        ConfRedactor confRedactor = new ConfRedactor(true);
        confRedactor.redactor();
    }
}
