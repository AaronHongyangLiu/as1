package com.example.aaron.hliu11_fueltrack;

import java.util.ArrayList;

/**
 * Created by Aaron on 1/26/2016.
 */

// this class is used to store the total fuel cost of all the log entries
// and also convert it to the format that is going to be displayed
public class SumMessage {
    private ArrayList<LogEntry> logs;
    private float sum;


    public SumMessage(ArrayList<LogEntry> l){
        logs = l;
        sum = 0;
        for(LogEntry log: logs){
            float cost = Float.parseFloat(log.getFuelCost());
            sum += cost;
        }
    }

    public String toSting(){
        return "Total Fuel Cost: "+String.format("%.2f",sum)+" dollars";
    }
}
