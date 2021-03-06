package com.example.androidqunyinhui.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@Table(name = "TbStudent", database = AppDatabase.class)
public class TbStudent extends BaseModel{

    //自增ID
    @Column(name = "id")
    @PrimaryKey(autoincrement = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private int sex;

    @Column(name = "email")
    private String email;

    @Column(name = "class_grade", typeConverter=ClassGrade.ClassGradeConverter.class)
    private ClassGrade classGrade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ClassGrade getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(ClassGrade classGrade) {
        this.classGrade = classGrade;
    }

    @Override
    public String toString() {

        String s = "";
        if(classGrade != null){
            s = classGrade.toString();
        }

        return "id: "+id+"    name: "+name+"   sex: "+sex+"  email:"+email+"  classGrade:"+s+"\n";
    }
}
