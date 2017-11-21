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
import java.util.Iterator;
import java.util.LinkedHashMap;

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

    @Test
    public void test2(){
        String s = "张山张山";
        String re = s.substring(0, 1);
        String mi = s.substring(1, s.length());
        System.out.println(re+"  "+mi);
        System.out.println(re+mi.replaceAll(".","*"));
    }

    @Test
    public void testLinkHashMap(){
        // accessOrder 表示是否按顺序插入；
        // Linked内部含有一个private transient Entry header;来记录元素插入的顺序或者是元素被访问的顺序。
        // 利用这个线性结构的对象，可以帮助记录entry加入的前后顺序或者记录entry被访问的频率(最少被访问的entry靠前，最近访问的entry靠后)
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(100, 0.75f, true);
        String string1 = linkedHashMap.put("111", "aaa");
        String string2 = linkedHashMap.put("222", "bbb");
        String string3 = linkedHashMap.put("333", "ccc");
        // put 函数： 如果存在相同的key， 则返回之前的value
        String string4 = linkedHashMap.put("222", "bbb");

        System.out.println(string1+"  "+string2+"  "+string3+" "+string4);


        Iterator iterator = linkedHashMap.keySet().iterator( );
        while(iterator.hasNext( )) {
            String key = (String) iterator.next( );
            System.out.println(key);
        }

    }

}