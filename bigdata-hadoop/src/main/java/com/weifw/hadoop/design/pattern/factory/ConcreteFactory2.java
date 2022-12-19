package com.weifw.hadoop.design.pattern.factory;

public class ConcreteFactory2 implements AbstractFactory2 {

    @Override
    public String produceA2() {
        return "produceA2";
    }

    @Override
    public String produceB2() {
        return "produceB2";
    }
}
