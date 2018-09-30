package typesofdesignpatterns.creational.singleton;

/**
 * Created by ravi on 30/9/18.
 */
public class SingletonEagerInitialize {

    private static SingletonEagerInitialize instance = new SingletonEagerInitialize();

    private int value;

    private SingletonEagerInitialize() {
    }

    public static SingletonEagerInitialize getInstance() {

        if (instance == null) {
            System.out.println("First null check");
            synchronized (Singleton.class) {
                if (instance == null) {
                    System.out.println("Second null check");
                    instance = new SingletonEagerInitialize();
                }
            }
        }
        return instance;
    }

    public int getValue() {
        return value;
    }
}
