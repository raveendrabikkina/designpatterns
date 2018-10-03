package com.ravi.designpatterns.creational.singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by ravi on 30/9/18.
 */
final class SingletonLazyInitialize implements Serializable {

    private static final long SerialVersionUID = 2L;

    private static final Logger LOG = LogManager.getLogger(SingletonLazyInitialize.class.getName());

    private static volatile SingletonLazyInitialize instance;

    private int value;

    /**
     * Setting constructor private to prevent instantiation from other classes.
     */
    private SingletonLazyInitialize() {
        if (Objects.nonNull(instance)) {
            throw new RuntimeException("Preventing object creation through reflection for class:"
                    + SingletonLazyInitialize.class.getName());
        }
    }

    /**
     * This method returns the Object of this class.
     *
     * @return
     */
    public static SingletonLazyInitialize getInstance() {

        if (instance == null) {
            synchronized (SingletonLazyInitialize.class) {
                if (instance == null) {
                    LOG.debug("Creating instance for class SingletonLazyInitialize");
                    instance = new SingletonLazyInitialize();
                }
            }
        }
        return instance;
    }


    /**
     * This is to prevent cloning of this singleton class
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {

        final String message = "Cannot clone Singleton:" + this.getClass().getName();
        throw new CloneNotSupportedException(message);
    }

    /**
     * This method is used for replacing the object read from the stream
     *
     * @return
     */
    protected Object readResolve() {
        return instance;
    }

    public int getValue() {
        return value;
    }
}
