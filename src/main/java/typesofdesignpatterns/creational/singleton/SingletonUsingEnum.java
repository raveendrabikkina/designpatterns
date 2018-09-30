package typesofdesignpatterns.creational.singleton;

/**
 * Created by ravi on 30/9/18.
 */
public enum SingletonUsingEnum {

    INSTANCE1(1),

    INSTANCE2(2),

    INSTANCE3(3);

    int value;

    SingletonUsingEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}