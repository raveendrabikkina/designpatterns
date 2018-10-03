package com.ravi.designpatterns.creational.singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ravi.designpatterns.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ravi on 30/9/18.
 */
public class ClientSingleton {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger LOG = LogManager.getLogger(ClientSingleton.class.getName());
    private static final Set<String> set = new ConcurrentSkipListSet<>();
    private static final int cores = Runtime.getRuntime().availableProcessors();

    public static void main(final String[] args) {

       /*
        final SingletonLazyInitialize lazySingleton = new SingletonLazyInitialize();
        final SingletonEagerInitialize eagerSingleton = new SingletonEagerInitialize();
       */
        LOG.info("################[enumTest]################");
        enumTest();

        LOG.info("################[serializationTest]################");
        serializationTest();

        LOG.info("################[jsonStringTest]################");
        jsonStringTest();

        LOG.info("################[jsonFileTest]################");
        jsonFileTest();
    }

    private static void jsonFileTest() {
        final ExecutorService executorService = Executors.newFixedThreadPool(cores);
        for (int i = 1; i <= cores; i++) {
            executorService.submit(new JSonFileTask());
        }
        executorService.shutdown();
    }

    private static void jsonStringTest() {
        try {

            final SingletonLazyInitialize instance1 = SingletonLazyInitialize.getInstance();
            LOG.debug("SingletonLazyInitialize:Instance1:{}", instance1.hashCode());
            final String objectToJsonInstance1 = JsonUtil.objectToJson(instance1);
            final SingletonLazyInitialize instance2 = JsonUtil.jsonToObject(objectToJsonInstance1, SingletonLazyInitialize.class);
            LOG.debug("SingletonLazyInitialize:Instance2:{}", instance2);


            final SingletonEagerInitialize instance3 = SingletonEagerInitialize.getInstance();
            LOG.debug("SingletonEagerInitialize:Instance3:{}", instance3.hashCode());
            final String objectToJsonInstance3 = JsonUtil.objectToJson(instance3);
            final SingletonEagerInitialize instance4 = JsonUtil.jsonToObject(objectToJsonInstance3, SingletonEagerInitialize.class);
            LOG.debug("SingletonEagerInitialize Instance4:{}", instance4);
            final SingletonEagerInitialize tryToBreak = JsonUtil.jsonToObject(objectToJsonInstance3, SingletonEagerInitialize.class);
            LOG.debug("Breaking SingletonEagerInitialize:{}", tryToBreak);

        } catch (final RuntimeException e) {
            LOG.error(e.getMessage());
        }
    }

    private static void serializationTest() {
        final String lazyFile = "output/file1.text";
        final String eagerFile = "output/file2.text";

        singletonLazyCheck(lazyFile);
        singletonEagerCheck(eagerFile);
        try {
            Thread.sleep(1000);
        } catch (final InterruptedException e) {
            LOG.error(e.getMessage());
        }
        set.stream().forEach(LOG::debug);
    }

    private static void enumTest() {
        for (final SingletonUsingEnum singletonUsingEnum : SingletonUsingEnum.values()) {
            LOG.debug("Name:{}", singletonUsingEnum.name());
            LOG.debug("Value:{}", singletonUsingEnum.getValue());
        }
    }

    private static void singletonEagerCheck(final String file) {
        final ExecutorService executorService = Executors.newFixedThreadPool(cores);
        for (int i = 1; i <= cores; i++) {
            executorService.submit(new SingletonEagerTask(file, set));
        }
        executorService.shutdown();
    }

    private static void singletonLazyCheck(final String file) {
        final ExecutorService executorService = Executors.newFixedThreadPool(cores);
        for (int i = 1; i <= cores; i++) {
            executorService.submit(new SingletonLazyTask(file, set));
        }
        executorService.shutdown();
    }
}
