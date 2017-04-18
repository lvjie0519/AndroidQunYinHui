package com.example.androidqunyinhui;

import org.junit.Test;

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
        String str = "";
        String []array = str.split("\\s+");
        System.out.println(array.length);
        for(int i=0; i<array.length; i++ ){
            System.out.println(array[i]);
        }

        assertEquals(4, 2 + 2);
    }

}