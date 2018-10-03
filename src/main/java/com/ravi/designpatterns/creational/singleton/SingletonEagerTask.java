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
class SingletonEagerTask implements Runnable {

    private static final Logger LOG = LogManager.getLogger(SingletonEagerTask.class.getName());

    private final String file;
    private final Set<String> set;

    SingletonEagerTask(final String file, final Set<String> set) {
        this.file = file;
        this.set = set;
    }

    @Override
    public void run() {
        try {
            final SingletonEagerInitialize instance2 = SingletonEagerInitialize.getInstance();
            eagerSingletonSerializationCheck(instance2, file);
        } catch (final ClassNotFoundException e) {
            LOG.error(e.getMessage());
        }
    }

    private void serializeEagerSingleton(final SingletonEagerInitialize instance, final String file)
            throws IOException {
        final ObjectOutput out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(instance);
        out.close();
    }

    private void eagerSingletonSerializationCheck(final SingletonEagerInitialize instance, final String file)
            throws ClassNotFoundException {
        try {
            serializeEagerSingleton(instance, file);
            set.add(instance.getClass().getName() + "Instance1:" + String.valueOf(instance.hashCode()));
            set.add(instance.getClass().getName() + "Instance2:" + String.valueOf(deserialize(file).hashCode()));
        } catch (final IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
