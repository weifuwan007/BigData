package com.weifw.hadoop.rpc.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Java 动态代理
 */
public class DynamicProxyHandler implements InvocationHandler {

    private static final Logger LOG = LoggerFactory.getLogger(DynamicProxyHandler.class);

    private final Object obj;

    public DynamicProxyHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOG.info("proxy -> " + proxy.getClass() + ", method -> " + method);
        return method.invoke(obj, args);
    }
}
