package com.ravi.designpatterns.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.ravi.designpatterns.creational.singleton.ClientSingleton.OBJECT_MAPPER;

public final class JsonUtil {

    private static final Logger LOG = LogManager.getLogger(JsonUtil.class.getName());

    private JsonUtil() {

    }

    public static <T> String objectToJson(final T obj) {
        String jsonString = "";

        try {
            jsonString = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (final Exception e) {
            LOG.error(e.getMessage());
        }

        return jsonString;
    }

    public static <T> T jsonToObject(final String jsonString, final Class<T> clazz) {
        T obj = null;
        try {
            obj = OBJECT_MAPPER.readValue(jsonString, clazz);
        } catch (final Exception e) {
            LOG.error(e.getMessage());
        }

        return obj;
    }
}