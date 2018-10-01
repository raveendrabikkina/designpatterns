package typesofdesignpatterns.creational.singleton;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ravi on 30/9/18.
 */
public class ClientSingleton {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Set<String> set = new ConcurrentSkipListSet<>();

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
        final ExecutorService executorService = Executors.newFixedThreadPool(7);
        for (int i = 1; i <= 1; i++) {
            executorService.submit(new JSonFileTask(mapper));
        }
        executorService.shutdown();
    }

    private static void jsonStringTest() {
        //TODO: Broken singleton fix it
        final SingletonLazyInitialization instance1 = SingletonLazyInitialization.getInstance();
        System.out.println("SingletonLazyInitialization:instance1:" + instance1.hashCode());
        final String objectToJsonInstance1 = objectToJson(instance1);
        final SingletonLazyInitialization instance2 = jsonToObject(objectToJsonInstance1, SingletonLazyInitialization.class);
        System.out.println("SingletonLazyInitialization:instance2:" + instance2.hashCode());


        final SingletonEagerInitialize instance3 = SingletonEagerInitialize.getInstance();
        System.out.println("SingletonEagerInitialize:instance3:" + instance3.hashCode());
        final String objectToJsonInstance3 = objectToJson(instance3);
        final SingletonEagerInitialize instance4 = jsonToObject(objectToJsonInstance3, SingletonEagerInitialize.class);
        System.out.println("SingletonEagerInitialize instance4:" + instance4.hashCode());
        final SingletonEagerInitialize tryToBreak = jsonToObject(objectToJsonInstance3, SingletonEagerInitialize.class);
        System.out.println("Breaking SingletonEagerInitialize:" + tryToBreak.hashCode());

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
        final ExecutorService executorService = Executors.newFixedThreadPool(7);
        for (int i = 1; i <= 7; i++) {
            executorService.submit(new SingletonEagerTask(file, set, mapper));
        }
        executorService.shutdown();
    }

    private static void singletonLazyCheck(final String file) {
        final ExecutorService executorService = Executors.newFixedThreadPool(7);
        for (int i = 1; i <= 7; i++) {
            executorService.submit(new SingletonLazyTask(file, set, mapper));
        }
        executorService.shutdown();
    }

    public static Object deserialize(final String file) throws IOException, ClassNotFoundException {
        // deserialize from file to object
        final ObjectInput in = new ObjectInputStream(new FileInputStream(file));

        final Object instance = in.readObject();
        in.close();
        return instance;
    }

    public static <T> String objectToJson(final T obj) {
        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(obj);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public static <T> T jsonToObject(final String jsonString, final Class<T> clazz) {
        T obj = null;
        try {
            obj = mapper.readValue(jsonString, clazz);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

}
