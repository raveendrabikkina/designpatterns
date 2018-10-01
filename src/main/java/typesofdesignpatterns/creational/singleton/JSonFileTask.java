package typesofdesignpatterns.creational.singleton;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by ravi on 1/10/18.
 */
public class JSonFileTask implements Runnable {

    private final ObjectMapper mapper;

    JSonFileTask(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void run() {
        try {
            final SingletonLazyInitialization lazySingleton = SingletonLazyInitialization.getInstance();
            System.out.println("before serialization SingletonLazyInitialization:instance1:" + lazySingleton.hashCode());
            final String pathname1 = "output/lazySingleton.json";
            mapper.writeValue(new File(pathname1), lazySingleton);
            System.out.println("after deserialization SingletonLazyInitialization:instance1:" + lazySingleton.hashCode());

            final SingletonEagerInitialize eagerSingleton = SingletonEagerInitialize.getInstance();
            System.out.println("before serialization SingletonEagerInitialize:instance2:" + eagerSingleton.hashCode());
            final String pathname2 = "output/eagerSingleton.json";
            mapper.writeValue(new File(pathname2), eagerSingleton);
            //Files.lines(Paths.get(pathname2), StandardCharsets.UTF_8).forEach(System.out::println);
            System.out.println("after deserialization SingletonEagerInitialize:instance2:" + eagerSingleton.hashCode());

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
