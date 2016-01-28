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

public class MainActivity extends Activity {

    protected static final String FILENAME = "file.sav";
    protected static int ID;
    private ListView logList;
    private TextView sumText;

    protected static ArrayList<LogEntry> entrys = new ArrayList<LogEntry>();
    private SumMessage sum;
    private ArrayAdapter<LogEntry> adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mainAddButton = (Button)findViewById(R.id.mainAddButton);
        final Button editButton = (Button)findViewById(R.id.mainEditButton);
        sumText =(TextView)findViewById(R.id.sum);
        logList = (ListView) findViewById(R.id.logEntries);
        logList.setAdapter(adapter1);

        mainAddButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.aaron.hliu11_fueltrack.AddActivity");
                        startActivity(intent);

                        adapter1.notifyDataSetChanged();
                        sum = new SumMessage(entrys);
                        sumText.setText(sum.toSting());
                    }
                }
        );

        logList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        view.setSelected(true);
                        final Intent i = new Intent("com.example.aaron.hliu11_fueltrack.EditActivity");
                        ID = (int)id;
                        editButton.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(i);

                                        adapter1.notifyDataSetChanged();
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
        loadFromFile();
        adapter1 = new ArrayAdapter<LogEntry>(this, R.layout.list_item, entrys);
        logList.setAdapter(adapter1);
        sum = new SumMessage(entrys);
        sumText.setText(sum.toSting());

    }

    private void loadFromFile(){
        try{
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<LogEntry>>(){}.getType();
            entrys = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            entrys = new ArrayList<LogEntry>();

        }
    }

    private void saveInFile(){
        try{
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(entrys,out);
            out.flush();

            fos.close();
        }catch(FileNotFoundException e){
            throw new RuntimeException();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

