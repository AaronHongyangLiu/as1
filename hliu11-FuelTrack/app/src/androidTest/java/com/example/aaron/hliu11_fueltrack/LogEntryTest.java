package com.example.aaron.hliu11_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Aaron on 1/27/2016.
 */
public class LogEntryTest extends ActivityInstrumentationTestCase2{
    public LogEntryTest(){
        super(MainActivity.class);
    }

    public void testCalFuelCost(){
        LogEntry my = new LogEntry();

        my.setUnitCost("2.5");
        my.setFuelAmount("4");

        my.calFuelCost();

        assertEquals(my.getFuelCost(),"10.000000");
    }

}
