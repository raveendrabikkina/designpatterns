package com.ravi.designpatterns.creational.singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by ravi on 30/9/18.
 */
final class SingletonEagerInitialize implements Serializable, Cloneable {

    private static final long SerialVersionUID = 1L;

    private static final Logger LOG = LogManager.getLogger(SingletonEagerInitialize.class.getName());

    private static volatile SingletonEagerInitialize instance = new SingletonEagerInitialize();

    private int value;

    private SingletonEagerInitialize() {
        if (Objects.nonNull(instance)) {
            throw new RuntimeException("Cannot instantiate singleton!!!");
        }
    }

    public static SingletonEagerInitialize getInstance() {

        if (instance == null) {
            LOG.debug("First null check");
            synchronized (SingletonEagerInitialize.class) {
                if (instance == null) {
                    LOG.debug("Second null check");
                    instance = new SingletonEagerInitialize();
                }
            }
        }
        return instance;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        final String message = "Cannot clone Singleton:" + this.getClass().getName();
        throw new CloneNotSupportedException(message);
    }

    protected Object readResolve() {
        return instance;
    }

    public int getValue() {
        return value;
    }
}
