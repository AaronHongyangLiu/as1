package com.example.aaron.hliu11_fueltrack;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

// this is the main activity class
public class MainActivity extends Activity {

    // data file name
    protected static final String FILENAME = "file.sav";
    // ID of the selected entry
    protected static int ID;

    private ListView logList;
    private TextView sumText;
    private ArrayAdapter<LogEntry> adapter1;

    // list to store log datas
    protected static ArrayList<LogEntry> entrys = new ArrayList<LogEntry>();
    // the total fuel cost summation message
    private SumMessage sum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // line button and views in layout
        Button mainAddButton = (Button)findViewById(R.id.mainAddButton);
        // it's final is because this button will be used inside itemClickLister
        final Button editButton = (Button)findViewById(R.id.mainEditButton);
        sumText =(TextView)findViewById(R.id.sum);
        logList = (ListView) findViewById(R.id.logEntries);
        logList.setAdapter(adapter1);

        // if user click on add
        mainAddButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //set up an intent to AddActivity
                        Intent intent = new Intent("com.example.aaron.hliu11_fueltrack.AddActivity");
                        startActivity(intent);

                        // notify possible data changes after get back from AddActivity
                        adapter1.notifyDataSetChanged();
                        // recalculate the total fuel cost
                        sum = new SumMessage(entrys);
                        sumText.setText(sum.toSting());
                    }
                }
        );

        // if user click on one log entry
        logList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // set selected to be true to change background color of that entry
                        view.setSelected(true);
                        // set up an intent to EditActivity
                        final Intent i = new Intent("com.example.aaron.hliu11_fueltrack.EditActivity");
                        // store the id of the selected entry
                        ID = (int) id;
                        // if user click on edit button
                        editButton.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        // go to EditActivity
                                        startActivity(i);

                                        // notify possible data changes after get back from EditActivity
                                        adapter1.notifyDataSetChanged();
                                        // recalculate the total fuel cost
                                        sum = new SumMessage(entrys);
                                        sumText.setText(sum.toSting());
                                    }
                                }
                        );
                    }
                }
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        // load data file
        loadFromFile();
        // setup adpter
        adapter1 = new ArrayAdapter<LogEntry>(this, R.layout.list_item, entrys);
        logList.setAdapter(adapter1);
        // calculate the total fuel cost
        sum = new SumMessage(entrys);
        sumText.setText(sum.toSting());

    }

    private void loadFromFile(){
        try{
            // open the input data file
            FileInputStream fis = openFileInput(FILENAME);
            //setup a reader
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            //setup gson
            Gson gson = new Gson();

            // setup my type
            Type listType = new TypeToken<ArrayList<LogEntry>>(){}.getType();
            // load data to the list of log entries
            entrys = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            // if there is no data file, then create an empty list
            entrys = new ArrayList<LogEntry>();

        }
    }


}

