package typesofdesignpatterns.creational.singleton;

import java.io.Serializable;

/**
 * Created by ravi on 30/9/18.
 */
public final class SingletonLazyInitialization implements Serializable {

    private static SingletonLazyInitialization instance;

    private int value;

    private SingletonLazyInitialization() {
    }

    public static SingletonLazyInitialization getInstance() {

        if (instance == null) {
            synchronized (SingletonLazyInitialization.class) {
                if (instance == null) {
                    instance = new SingletonLazyInitialization();
                } else {
                    System.out.println("returning singleton");
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
