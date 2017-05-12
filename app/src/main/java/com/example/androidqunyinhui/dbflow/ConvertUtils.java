package com.example.androidqunyinhui.dbflow;

import android.text.TextUtils;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public class ConvertUtils {

    public ConvertUtils() {
    }

    public static String getDBValue(Object model) {
        if(model == null) {
            return null;
        } else {
            try {
                return ObjectMapperUtils.getMapperInstance().writeValueAsString(model);
            } catch (JsonProcessingException var2) {
                var2.printStackTrace();
                return null;
            }
        }
    }

    public static <T> T getModelValue(String data, Class<T> clazz) {
        if(TextUtils.isEmpty(data)) {
            return null;
        } else {
            ObjectMapper mapper = new ObjectMapper();

            try {
                return mapper.readValue(data, clazz);
            } catch (IOException var4) {
                Log.e("ConvertUtils", var4.toString());
                return null;
            }
        }
    }

    public static <T> List<T> getModelListValue(String data, Class<T> clazz) {
        if(TextUtils.isEmpty(data)) {
            return null;
        } else {
            JavaType type = ObjectMapperUtils.constructParametricType(ArrayList.class, new Class[]{clazz});

            try {
                return (List)ObjectMapperUtils.getMapperInstance().readValue(data, type);
            } catch (IOException var4) {
                Log.e("ConvertUtils", var4.toString());
                return null;
            }
        }
    }

}
