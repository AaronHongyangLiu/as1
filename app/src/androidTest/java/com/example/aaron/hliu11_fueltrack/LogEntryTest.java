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

        assertEquals(my.getFuelCost(),"10.00");
    }

    public void testGetValid(){
        LogEntry my = new LogEntry();

        // when fuelAmount is not numerical
        my.setFuelAmount("a");
        assertFalse(my.getValid());
        // fuelAmout is numerical
        my.setFuelAmount("1");
        assertTrue(my.getValid());

        // when odometer is not numercal
        my.setOdometer("b");
        assertFalse(my.getValid());
        // odometer is numerical
        my.setOdometer("2");
        assertTrue(my.getValid());

        // when unitCost is not numerical
        my.setUnitCost("c");
        assertFalse(my.getValid());
        // unitCost is numerical
        my.setUnitCost("3");
        assertTrue(my.getValid());
    }


    public void testToString(){
        LogEntry my = new LogEntry();
        final String format = "2016-01-18, Costco, 200123.5 km, regular, 10.000 L, 1.0 cents/L, 10.00 dollars";

        my.setDate(new MyDate("2016","01","18"));
        my.setStation("Costco");
        my.setOdometer("200123.5");
        my.setFuelGrade("regular");
        my.setFuelAmount("10");
        my.setUnitCost("1");
        my.calFuelCost();
        my.getValid();

        assertEquals(my.toString(),format);
    }
}
