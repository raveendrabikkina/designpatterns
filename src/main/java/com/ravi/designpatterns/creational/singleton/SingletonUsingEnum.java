package com.ravi.designpatterns.creational.singleton;

/**
 * Created by ravi on 30/9/18.
 */
enum SingletonUsingEnum {

    INSTANCE1(1),

    INSTANCE2(2),

    INSTANCE3(3);

    int value;

    SingletonUsingEnum(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value * 5;
    }
}