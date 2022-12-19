package com.weifw.hadoop.design.pattern.factory;

/**
 * 提供生产一系列或者相互依赖的对象的接口。
 */
public interface AbstractFactory {
    AbstractFactory1 createAbstractFactory1();

    AbstractFactory2 createAbstractFactory2();
}
