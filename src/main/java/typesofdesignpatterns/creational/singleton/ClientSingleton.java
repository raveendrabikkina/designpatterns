package typesofdesignpatterns.creational.singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import typesofdesignpatterns.util.JsonUtil;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ravi on 30/9/18.
 */
public class ClientSingleton {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Set<String> set = new ConcurrentSkipListSet<>();
    private static final int cores = Runtime.getRuntime().availableProcessors();

    public static void main(final String[] args) {

       /*
        final SingletonLazyInitialization lazySingleton = new SingletonLazyInitialization();
        final SingletonEagerInitialize eagerSingleton = new SingletonEagerInitialize();
       */
        System.out.println("################[enumTest]################");
        enumTest();

        System.out.println("################[serializationTest]################");
        serializationTest();

        System.out.println("################[jsonStringTest]################");
        jsonStringTest();

        System.out.println("################[jsonFileTest]################");
        jsonFileTest();
    }

    private static void jsonFileTest() {
        final ExecutorService executorService = Executors.newFixedThreadPool(cores);
        for (int i = 1; i <= 7; i++) {
            executorService.submit(new JSonFileTask());
        }
        executorService.shutdown();
    }

    private static void jsonStringTest() {
        try {

            final SingletonLazyInitialization instance1 = SingletonLazyInitialization.getInstance();
            System.out.println("SingletonLazyInitialization:instance1:" + instance1.hashCode());
            final String objectToJsonInstance1 = JsonUtil.objectToJson(instance1);
            final SingletonLazyInitialization instance2 = JsonUtil.jsonToObject(objectToJsonInstance1, SingletonLazyInitialization.class);
            System.out.println("SingletonLazyInitialization:instance2:" + instance2.hashCode());


            final SingletonEagerInitialize instance3 = SingletonEagerInitialize.getInstance();
            System.out.println("SingletonEagerInitialize:instance3:" + instance3.hashCode());
            final String objectToJsonInstance3 = JsonUtil.objectToJson(instance3);
            final SingletonEagerInitialize instance4 = JsonUtil.jsonToObject(objectToJsonInstance3, SingletonEagerInitialize.class);
            System.out.println("SingletonEagerInitialize instance4:" + instance4.hashCode());
            final SingletonEagerInitialize tryToBreak = JsonUtil.jsonToObject(objectToJsonInstance3, SingletonEagerInitialize.class);
            System.out.println("Breaking SingletonEagerInitialize:" + tryToBreak.hashCode());

        } catch (final RuntimeException e) {
            System.out.println(e.getMessage());
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
            e.printStackTrace();
        }
        set.stream().forEach(System.out::println);
    }

    private static void enumTest() {
        for (final SingletonUsingEnum singletonUsingEnum : SingletonUsingEnum.values()) {
            System.out.println(singletonUsingEnum.name());
        }
    }

    private static void singletonEagerCheck(final String file) {
        final ExecutorService executorService = Executors.newFixedThreadPool(cores);
        for (int i = 1; i <= 7; i++) {
            executorService.submit(new SingletonEagerTask(file, set));
        }
        executorService.shutdown();
    }

    private static void singletonLazyCheck(final String file) {
        final ExecutorService executorService = Executors.newFixedThreadPool(cores);
        for (int i = 1; i <= 7; i++) {
            executorService.submit(new SingletonLazyTask(file, set));
        }
        executorService.shutdown();
    }
}
