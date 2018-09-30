package typesofdesignpatterns.creational.singleton;

/**
 * Created by ravi on 30/9/18.
 */
public class TestSingleton {

    public static void main(String[] args) {
        for (SingletonUsingEnum singletonUsingEnum : SingletonUsingEnum.values()) {
            System.out.println(singletonUsingEnum.getValue());
        }
        //Singleton singleton = new Singleton();
        for (int i = 1; i <= 7; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    Singleton singleton = Singleton.getInstance();
                    System.out.println(singleton);
                }
            };
            t.start();
        }

        //Singleton singleton = new Singleton();
        for (int i = 1; i <= 7; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    SingletonEagerInitialize singleton = SingletonEagerInitialize.getInstance();
                    System.out.println(singleton);
                }
            };
            t.start();
        }

    }
}
