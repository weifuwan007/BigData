package com.weifw.hadoop.rpc.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 代理模式: 相当于中介，可以在执行方法前和后执行相关逻辑
 */
public interface ProxyPattern {

    void rerun();

    void rename();

}

class RealObject implements ProxyPattern {
    private static final Logger LOG = LoggerFactory.getLogger(RealObject.class);

    @Override
    public void rerun() {
        LOG.info("rerun = " + true);
    }

    @Override
    public void rename() {
        LOG.info("rename = " + true);
    }
}

class RealProxy implements ProxyPattern {

    private final static Logger LOG = LoggerFactory.getLogger(RealProxy.class);


    private final RealObject realObject;

    public RealProxy(RealObject realObject) {
        this.realObject = realObject;
    }


    @Override
    public void rerun() {
        LOG.info("rerun before ~~");
        realObject.rerun();
    }

    @Override
    public void rename() {
        LOG.info("rename before ~~");
        realObject.rename();
    }
}

