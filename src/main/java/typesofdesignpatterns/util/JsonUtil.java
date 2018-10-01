package typesofdesignpatterns.util;

import static typesofdesignpatterns.creational.singleton.ClientSingleton.OBJECT_MAPPER;

public final class JsonUtil {

    private JsonUtil() {
        
    }

    public static <T> String objectToJson(final T obj) {
        String jsonString = "";

        try {
            jsonString = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public static <T> T jsonToObject(final String jsonString, final Class<T> clazz) {
        T obj = null;
        try {
            obj = OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
}