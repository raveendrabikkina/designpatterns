package typesofdesignpatterns.creational.singleton;

import java.io.File;
import java.io.IOException;

import static typesofdesignpatterns.creational.singleton.ClientSingleton.OBJECT_MAPPER;

/**
 * Created by ravi on 1/10/18.
 */
public class JSonFileTask implements Runnable {

    @Override
    public void run() {
        try {
            final SingletonLazyInitialization lazySingleton = SingletonLazyInitialization.getInstance();
            System.out.println("Before serialization SingletonLazyInitialization:instance1:" + lazySingleton.hashCode());
            final String pathname1 = "output/lazySingleton.json";
            OBJECT_MAPPER.writeValue(new File(pathname1), lazySingleton);
            System.out.println("After deserialization SingletonLazyInitialization:instance1:" + lazySingleton.hashCode());

            final SingletonEagerInitialize eagerSingleton = SingletonEagerInitialize.getInstance();
            System.out.println("Before serialization SingletonEagerInitialize:instance2:" + eagerSingleton.hashCode());
            final String pathname2 = "output/eagerSingleton.json";
            OBJECT_MAPPER.writeValue(new File(pathname2), eagerSingleton);
            //Files.lines(Paths.get(pathname2), StandardCharsets.UTF_8).forEach(System.out::println);
            System.out.println("After deserialization SingletonEagerInitialize:instance2:" + eagerSingleton.hashCode());

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
