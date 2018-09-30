package typesofdesignpatterns.creational.singleton;

/**
 * Created by ravi on 30/9/18.
 */
public class Singleton {

    private static Singleton instance;

    private int value;

    private Singleton() {
    }

    public static Singleton getInstance() {

        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                } else {
                    System.out.println("returning");
                }
            }
        }
        return instance;
    }

    public int getValue() {
        return value;
    }
}
