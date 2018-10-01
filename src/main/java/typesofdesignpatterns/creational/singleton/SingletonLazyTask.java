package typesofdesignpatterns.creational.singleton;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Set;

import static typesofdesignpatterns.util.SerializationUtil.deserialize;

/**
 * Created by ravi on 1/10/18.
 */
public class SingletonLazyTask implements Runnable {

    private final String file;
    private final Set<String> set;

    SingletonLazyTask(final String file, final Set<String> set) {
        this.file = file;
        this.set = set;
    }

    private void LazySingletonSerializationCheck(final SingletonLazyInitialization instance, final String file)
            throws ClassNotFoundException {
        try {
            serializeLazySingleton(instance, file);
            set.add(instance.getClass().getName() + "Instance1:" + String.valueOf(instance.hashCode()));
            set.add(instance.getClass().getName() + "instance2:" + String.valueOf(deserialize(file).hashCode()));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    private void serializeLazySingleton(final SingletonLazyInitialization instance, final String file)
            throws IOException {
        final ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(instance);
        out.close();
    }

    @Override
    public void run() {
        try {
            final SingletonLazyInitialization instance = SingletonLazyInitialization.getInstance();
            LazySingletonSerializationCheck(instance, file);
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
