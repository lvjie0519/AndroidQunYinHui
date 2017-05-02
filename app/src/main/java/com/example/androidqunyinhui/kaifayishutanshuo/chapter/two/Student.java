package com.example.androidqunyinhui.kaifayishutanshuo.chapter.two;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
public class Student implements Serializable{

    private static final long serialVersionUID = 111222333444555l;

    private String name;
    private int age;
    private String phone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "name: "+name+"  age: "+age +"  phone: "+phone;
    }
}
