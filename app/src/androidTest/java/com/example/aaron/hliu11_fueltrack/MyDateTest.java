package com.example.aaron.hliu11_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by hliu11 on 1/28/16.
 */
public class MyDateTest extends ActivityInstrumentationTestCase2 {
    public MyDateTest(){
        super(MainActivity.class);
    }

    public void testToString(){
        MyDate my = new MyDate("1111","10","11");

        assertEquals(my.toString(), "1111-10-11");
    }
}
