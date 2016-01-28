package com.example.aaron.hliu11_fueltrack;

/**
 * Created by Aaron on 1/26/2016.
 */
public class LogEntry {
    private MyDate date;
    private String station;
    private String odometer;
    private String fuelGrade;
    private String fuelAmount;
    private String unitCost;
    private String fuelCost;
    private boolean valid;

    public LogEntry(){
        date = new MyDate();
        station = "Unknown Station";
        odometer = "0";
        fuelGrade = "Unknow Grade";
        fuelAmount = "0";
        unitCost = "0";
        fuelCost = "";
        valid = false;
    }

    // getters
    public MyDate getDate() {return date;}
    public String getStation() {return station;}
    public String getFuelCost() {return fuelCost;}
    public String getUnitCost() {return unitCost;}
    public String getFuelAmount() {return fuelAmount;}
    public String getFuelGrade() {return fuelGrade;}
    public String getOdometer() {return odometer;}
    public boolean getValid() {
        try{
            float od = Float.parseFloat(odometer);
            float fA = Float.parseFloat(fuelAmount);
            float uC = Float.parseFloat(unitCost);
            odometer = String.format("%.1f",od);
            fuelAmount = String.format("%.3f",fA);
            unitCost = String.format("%.1f",uC);
            valid = true;
        }catch(NumberFormatException e){
            valid = false;
        }
        return valid;
    }

    //setters
    public void setDate(MyDate date) {this.date = date;}
    public void setUnitCost(String unitCost) {this.unitCost = unitCost;}
    public void setFuelAmount(String fuelAmount) {this.fuelAmount = fuelAmount;}
    public void setFuelGrade(String fuelGrade) {this.fuelGrade = fuelGrade;}
    public void setOdometer(String odometer) {this.odometer = odometer;}
    public void setStation(String station) {this.station = station;}

    public void calFuelCost(){
        float a = Float.parseFloat(fuelAmount);
        float b = Float.parseFloat(unitCost);
        float cost = a * b;
        fuelCost = String.format("%.2f", cost);
    }

    @Override
    public String toString(){
        return date+", "+station+", "+odometer+" km, "+fuelGrade+", "+fuelAmount+" L, "+unitCost+" cents/L, "+fuelCost+" dollars";
    }

}
