package com.ravi.designpatterns.creational.singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Set;

import static com.ravi.designpatterns.util.SerializationUtil.deserialize;

/**
 * Created by ravi on 1/10/18.
 */
class SingletonLazyTask implements Runnable {

    private static final Logger LOG = LogManager.getLogger(SingletonLazyTask.class.getName());

    private final String file;
    private final Set<String> set;

    SingletonLazyTask(final String file, final Set<String> set) {
        this.file = file;
        this.set = set;
    }

    private void lazySingletonSerializationCheck(final SingletonLazyInitialize instance, final String file)
            throws ClassNotFoundException {
        try {
            serializeLazySingleton(instance, file);
            set.add(instance.getClass().getName() + "Instance:" + String.valueOf(instance.hashCode()));
            set.add(instance.getClass().getName() + "Deserialize Instance:" + String.valueOf(deserialize(file).hashCode()));
        } catch (final IOException e) {
            LOG.error(e.getMessage());
        }
    }

    private void serializeLazySingleton(final SingletonLazyInitialize instance, final String file)
            throws IOException {
        final ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(instance);
        out.close();
    }

    @Override
    public void run() {
        try {
            final SingletonLazyInitialize instance = SingletonLazyInitialize.getInstance();
            lazySingletonSerializationCheck(instance, file);
        } catch (final ClassNotFoundException e) {
            LOG.error(e.getMessage());
        }
    }
}
