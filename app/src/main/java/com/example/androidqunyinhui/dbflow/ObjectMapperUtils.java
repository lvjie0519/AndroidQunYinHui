package com.example.androidqunyinhui.dbflow;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public class ObjectMapperUtils {

    private static final ObjectMapper OBJECT_MAPPER;

    public ObjectMapperUtils() {
    }

    public static ObjectMapper getMapperInstance() {
        return OBJECT_MAPPER;
    }

    public static JavaType constructParametricType(Class<?> collectionClass, Class... elementClasses) {
        return OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    static {
        OBJECT_MAPPER = (new ObjectMapper()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
