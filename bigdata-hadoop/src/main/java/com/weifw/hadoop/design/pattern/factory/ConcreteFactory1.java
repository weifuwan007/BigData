package com.weifw.hadoop.design.pattern.factory;

public class ConcreteFactory1 implements AbstractFactory1 {

    @Override
    public String produceA1() {
        return "produceA1";
    }

    @Override
    public String produceB1() {
        return "produceB1";
    }
}
