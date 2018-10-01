package typesofdesignpatterns.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

/**
 * Created by ravi on 1/10/18.
 */
public final class SerializationUtil {

    private SerializationUtil() {

    }

    public static Object deserialize(final String file) throws IOException, ClassNotFoundException {
        // deserialize from file to object
        final ObjectInput in = new ObjectInputStream(new FileInputStream(file));

        final Object instance = in.readObject();
        in.close();
        return instance;
    }

}
