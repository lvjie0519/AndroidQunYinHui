package com.example.androidqunyinhui.dbflow;

import android.util.Log;

import com.raizlabs.android.dbflow.converter.TypeConverter;

/**
 * Created by lvjie on 2017/5/11 0011.
 */
public class ClassGrade {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id: "+id+"   name: "+name;
    }

    public static class ClassGradeConverter extends TypeConverter<String, ClassGrade> {

        @Override
        public String getDBValue(ClassGrade model) {
            String result = ConvertUtils.getDBValue(model);
            Log.i("lvjie","MusicConverter-->result="+result);
            return result;
        }

        @Override
        public ClassGrade getModelValue(String data) {
            Log.i("lvjie","MusicConverter-->data="+data);
            return ConvertUtils.getModelValue(data, ClassGrade.class);
        }
    }
}
