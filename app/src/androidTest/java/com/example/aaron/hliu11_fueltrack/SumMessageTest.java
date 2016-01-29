package com.example.aaron.hliu11_fueltrack;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

/**
 * Created by hliu11 on 1/28/16.
 */
public class SumMessageTest extends ActivityInstrumentationTestCase2 {
    public SumMessageTest(){
        super(MainActivity.class);
    }

    public void testToString(){
        ArrayList<LogEntry> list = new ArrayList<LogEntry>();
        LogEntry my1 = new LogEntry();
        LogEntry my2 = new LogEntry();

        my1.setUnitCost("10");
        my1.setFuelAmount("10");
        my1.setOdometer("0");
        my1.getValid();
        my1.calFuelCost();

        my2.setUnitCost("20");
        my2.setFuelAmount("10");
        my2.setOdometer("0");
        my2.getValid();
        my2.calFuelCost();

        list.add(my1);
        list.add(my2);

        SumMessage s = new SumMessage(list);

        assertEquals(s.toSting(),"Total Fuel Cost: 300.00 dollars");
    }
}
