package com.example.aaron.hliu11_fueltrack;

/**
 * Created by Aaron on 1/26/2016.
 */

// this class stores the date and can convert it to a certain format
public class MyDate {
    private String year;
    private String month;
    private String day;

    public MyDate(){
        year = "Unknown Date";
        month = "";
        day = "";
    }

    public MyDate(String y, String m, String d){
        year = y;
        month = m;
        day = d;
        if (y=="" && m=="" && d==""){
            year = "Unknown Date";
        }


    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    @Override
    public String toString(){
        return year+"-"+month+"-"+day;
    }
}
