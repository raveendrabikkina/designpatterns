package typesofdesignpatterns.creational.singleton;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Set;

import static typesofdesignpatterns.creational.singleton.ClientSingleton.deserialize;

/**
 * Created by ravi on 1/10/18.
 */
public class SingletonEagerTask implements Runnable {
    private final String file;
    private final Set<String> set;
    private final ObjectMapper mapper;

    SingletonEagerTask(final String file, final Set<String> set, final ObjectMapper mapper) {
        this.file = file;
        this.set = set;
        this.mapper = mapper;
    }

    private static void serializeEagerSingleton(final SingletonEagerInitialize instance, final String file)
            throws IOException {
        final ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(instance);
        out.close();
    }

    private void EagerSingletonSerializationCheck(final SingletonEagerInitialize instance, final String file)
            throws ClassNotFoundException {
        try {
            serializeEagerSingleton(instance, file);
            set.add(instance.getClass().getName() + "Instance1:" + String.valueOf(instance.hashCode()));
            set.add(instance.getClass().getName() + "instance2:" + String.valueOf(deserialize(file).hashCode()));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            final SingletonEagerInitialize instance2 = SingletonEagerInitialize.getInstance();
            EagerSingletonSerializationCheck(instance2, file);
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
