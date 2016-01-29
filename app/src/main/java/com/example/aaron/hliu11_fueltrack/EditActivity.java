package com.example.aaron.hliu11_fueltrack;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class EditActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final EditText yearText = (EditText) findViewById(R.id.year2);
        final EditText monthText = (EditText) findViewById(R.id.month2);
        final EditText dayText = (EditText) findViewById(R.id.day2);
        final EditText stationText = (EditText) findViewById(R.id.station2);
        final EditText odometerText = (EditText) findViewById(R.id.odometer2);
        final EditText fGText = (EditText) findViewById(R.id.fuelGrade2);
        final EditText fAText = (EditText) findViewById(R.id.fuelAmount2);
        final EditText fUCText = (EditText) findViewById(R.id.fuelUCost2);
        Button save = (Button) findViewById(R.id.save2);
        Button cancel = (Button) findViewById(R.id.cancel2);


        yearText.setText(MainActivity.entrys.get(MainActivity.ID).getDate().getYear());
        monthText.setText(MainActivity.entrys.get(MainActivity.ID).getDate().getMonth());
        dayText.setText(MainActivity.entrys.get(MainActivity.ID).getDate().getDay());
        stationText.setText(MainActivity.entrys.get(MainActivity.ID).getStation());
        odometerText.setText(MainActivity.entrys.get(MainActivity.ID).getOdometer());
        fGText.setText(MainActivity.entrys.get(MainActivity.ID).getFuelGrade());
        fAText.setText(MainActivity.entrys.get(MainActivity.ID).getFuelAmount());
        fUCText.setText(MainActivity.entrys.get(MainActivity.ID).getUnitCost());

        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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

                        newEntry.setDate(date);
                        newEntry.setStation(station);
                        newEntry.setOdometer(odometer);
                        newEntry.setFuelAmount(fA);
                        newEntry.setFuelGrade(fG);
                        newEntry.setUnitCost(fUC);


                        if(newEntry.getValid()) {
                            newEntry.calFuelCost();
                            MainActivity.entrys.set(MainActivity.ID, newEntry);
                            saveInFile();
                            finish();
                        }
                    }
                }
        );

        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }

    private void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(MainActivity.FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
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
