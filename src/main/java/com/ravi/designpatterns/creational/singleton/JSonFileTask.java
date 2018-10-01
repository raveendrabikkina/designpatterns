package com.ravi.designpatterns.creational.singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

import static com.ravi.designpatterns.creational.singleton.ClientSingleton.OBJECT_MAPPER;

/**
 * Created by ravi on 1/10/18.
 */
public class JSonFileTask implements Runnable {

    private static final Logger LOG = LogManager.getLogger(JSonFileTask.class.getName());

    @Override
    public void run() {
        try {
            final SingletonLazyInitialize lazySingleton = SingletonLazyInitialize.getInstance();
            LOG.debug("Before serialization SingletonLazyInitialize:instance1:" + lazySingleton.hashCode());
            final String pathname1 = "output/lazySingleton.json";
            OBJECT_MAPPER.writeValue(new File(pathname1), lazySingleton);
            LOG.debug("After deserialization SingletonLazyInitialize:Instance1:" + lazySingleton.hashCode());

            final SingletonEagerInitialize eagerSingleton = SingletonEagerInitialize.getInstance();
            LOG.debug("Before serialization SingletonEagerInitialize:Instance2:" + eagerSingleton.hashCode());
            final String pathname2 = "output/eagerSingleton.json";
            OBJECT_MAPPER.writeValue(new File(pathname2), eagerSingleton);
            //Files.lines(Paths.get(pathname2), StandardCharsets.UTF_8).forEach(System.out::println);
            LOG.debug("After deserialization SingletonEagerInitialize:Instance2:" + eagerSingleton.hashCode());

        } catch (final IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
