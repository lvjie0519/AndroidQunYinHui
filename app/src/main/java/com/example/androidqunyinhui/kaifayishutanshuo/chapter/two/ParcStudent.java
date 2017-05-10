package com.example.androidqunyinhui.kaifayishutanshuo.chapter.two;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/4/28 0028.
 */
public class ParcStudent implements Parcelable{


    private String name;
    private int age;
    private String phone;

    public ParcStudent(){}

    public ParcStudent(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    // 反序列化
    // 读取Parcel里面数据时必须按照成员变量声明的顺序，Parcel数据来源上面writeToParcel方法，读出来的数据供逻辑层使用
    public static final Creator<ParcStudent> CREATOR = new Creator<ParcStudent>() {
        @Override
        public ParcStudent createFromParcel(Parcel in) {
            System.out.println("createFromParcel()...");
            return new ParcStudent(in.readString(), in.readInt(), in.readString());
        }

        @Override
        public ParcStudent[] newArray(int size) {
            System.out.println("newArray()...");
            return new ParcStudent[size];
        }
    };

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

    // 返回当前对象的内容描述，如果含有文件描述符，返回1，否则返回0，几乎所有情况都返回0；
    @Override
    public int describeContents() {

        System.out.println("describeContents()...");

        return 0;
    }

    // 完成序列化功能
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        System.out.println("writeToParcel()...");

        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(phone);
    }

}
