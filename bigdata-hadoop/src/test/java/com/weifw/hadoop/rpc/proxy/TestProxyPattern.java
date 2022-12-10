package com.weifw.hadoop.rpc.proxy;

import com.weifw.hadoop.test.MultiThreadedTestUtil;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for Proxy
 */
public class TestProxyPattern {

    @Test
    public void testProxyPattern() {
        RealObject realObject = new RealObject();
        consume(realObject);

        RealProxy realProxy = new RealProxy(realObject);
        consume(realProxy);
    }

    @Test
    public void testJavaProxy() {
        RealObject realObject = new RealObject();
        consume(realObject);

        ProxyPattern proxyPattern = (ProxyPattern) Proxy.newProxyInstance(
                ProxyPattern.class.getClassLoader(),
                new Class[]{ProxyPattern.class},
                new DynamicProxyHandler(realObject));
        consume(proxyPattern);

    }

    public void consume(ProxyPattern proxyPattern) {
        proxyPattern.rerun();
        proxyPattern.rename();
    }

    @Test
    public void multi() throws Exception {
        final AtomicInteger threadsRun = new AtomicInteger();

        MultiThreadedTestUtil.TestContext ctx = new MultiThreadedTestUtil.TestContext();
        // Add 3 threads to test.
        for (int i = 0; i < 3; i++) {
            ctx.addThread(new MultiThreadedTestUtil.TestingThread(ctx) {
                @Override
                public void doWork() throws Exception {
                    Thread.sleep(1000);
                    threadsRun.incrementAndGet();

                }
            });
        }
        ctx.startThreads();
        // Set a timeout period for threads to complete.
        ctx.waitFor(5000);
        assertEquals(3, threadsRun.get());
    }
}
