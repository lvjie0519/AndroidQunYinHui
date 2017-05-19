package com.example.androidqunyinhui;

import com.example.androidqunyinhui.kaifayishutanshuo.chapter.two.ParcStudent;
import com.example.androidqunyinhui.kaifayishutanshuo.chapter.two.Student;

import org.junit.Test;
import org.junit.runners.Suite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testString(){
//        String str = "";
//        String []array = str.split("\\s+");
//        System.out.println(array.length);
//        for(int i=0; i<array.length; i++ ){
//            System.out.println(array[i]);
//        }

        String temp = "[00:05.27]Listen to the tape then answer this question here my name is lvjie  ## 听录音并回到问题";
        if(temp.indexOf("[") != 0 || temp.indexOf("]") != 9 ){
            System.out.println("**************");
        }else{
            System.out.println("##############");
        }
        System.out.println(temp.indexOf("[")+"   "+temp.indexOf("]"));

        assertEquals(4, 2 + 2);
    }

    @Test
    public void testArray(){
        String string = "aaaaa bbb cc dd ";
        String []array = string.split("\\s+");
        int len = array.length;

        String []tempArray = string.split("\\s+", (len>>1)+1);

        int size = tempArray[tempArray.length-1].length();
        String temp1 = string.substring(0, string.length()-size);
        String temp2 = tempArray[tempArray.length-1];
        System.out.println("temp1: "+temp1);
        System.out.println("temp2: "+temp2);


    }

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {

        String file = "E:\\test\\test.txt";

//        Student student = new Student();
//        student.setName("lvjie");
//        student.setAge(21);
//
//        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
//        outputStream.writeObject(student);
//        outputStream.close();

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
        Student student = (Student) inputStream.readObject();
        System.out.println(student);
        inputStream.close();

    }

    @Test
    public void testParcelable() throws IOException {
        String file = "E:\\test\\test.txt";
        ParcStudent student = new ParcStudent("lvjie", 21, "15386207778");
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(student);
        outputStream.close();
    }

}