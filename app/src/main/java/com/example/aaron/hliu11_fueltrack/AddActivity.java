package com.example.aaron.hliu11_fueltrack;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AddActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // link the EditText and Button with the views in layout
        final EditText yearText = (EditText) findViewById(R.id.year);
        final EditText monthText = (EditText) findViewById(R.id.month);
        final EditText dayText = (EditText) findViewById(R.id.day);
        final EditText stationText = (EditText) findViewById(R.id.station);
        final EditText odometerText = (EditText) findViewById(R.id.odometer);
        final EditText fGText = (EditText) findViewById(R.id.fuelGrade);
        final EditText fAText = (EditText) findViewById(R.id.fuelAmount);
        final EditText fUCText = (EditText) findViewById(R.id.fuelUCost);
        Button save = (Button) findViewById(R.id.save);
        Button cancel = (Button) findViewById(R.id.cancel);

        //if user click save button
        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //get new log info
                        String year = yearText.getText().toString();
                        String month = monthText.getText().toString();
                        String day = dayText.getText().toString();
                        String station = stationText.getText().toString();
                        String odometer = odometerText.getText().toString();
                        String fA = fAText.getText().toString();
                        String fG = fGText.getText().toString();
                        String fUC = fUCText.getText().toString();
                        MyDate date = new MyDate(year, month, day);
                        LogEntry newEntry = new LogEntry();

                        //put the new info into a new entry
                        newEntry.setDate(date);
                        newEntry.setStation(station);
                        newEntry.setOdometer(odometer);
                        newEntry.setFuelAmount(fA);
                        newEntry.setFuelGrade(fG);
                        newEntry.setUnitCost(fUC);

                        // if the newEntry is valid
                        if (newEntry.getValid()) {
                            // calculate the cost
                            newEntry.calFuelCost();
                            // append it to the log entry list
                            MainActivity.entrys.add(newEntry);
                            // save data in file
                            saveInFile();
                            // go back to MainActivity
                            finish();
                        }
                    }
                }
        );
        //if the user click on cancel button
        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // go back to MainActivity
                        finish();
                    }
                }
        );
    }

    private void saveInFile(){
        try{
            // open the data file
            FileOutputStream fos = openFileOutput(MainActivity.FILENAME, Context.MODE_PRIVATE);
            // setup a writer
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            // set up gson
            Gson gson = new Gson();
            // write data in the file
            gson.toJson(MainActivity.entrys,out);
            out.flush();

            fos.close();
        }catch(FileNotFoundException e){
            throw new RuntimeException();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

