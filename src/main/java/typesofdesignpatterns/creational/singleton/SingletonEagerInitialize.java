package typesofdesignpatterns.creational.singleton;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by ravi on 30/9/18.
 */
public final class SingletonEagerInitialize implements Serializable, Cloneable {

    private static SingletonEagerInitialize instance = new SingletonEagerInitialize();

    private int value;

    private SingletonEagerInitialize() {
        if (Objects.nonNull(instance)) {
            throw new RuntimeException("Cannot instantiate singleton!!!");
        }
    }

    public static SingletonEagerInitialize getInstance() {

        if (instance == null) {
            System.out.println("First null check");
            synchronized (SingletonEagerInitialize.class) {
                if (instance == null) {
                    System.out.println("Second null check");
                    instance = new SingletonEagerInitialize();
                }
            }
        }
        return instance;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        final String message = "Cannot clone Singleton:" + this.getClass().getName();
        throw new CloneNotSupportedException(message);
    }

    protected Object readResolve() {
        return instance;
    }

    public int getValue() {
        return value;
    }
}
