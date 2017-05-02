package com.example.androidqunyinhui.self.define.view;

/**
 * Created by lvjie on 2017/4/28 0028.
 */
public class SelfData {

    public static SelfData INSTANCE;

    private String data;
    private SelfData(){}

    public static SelfData getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new SelfData();
            INSTANCE.setData("aaa");
        }
        return INSTANCE;
    }


    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
