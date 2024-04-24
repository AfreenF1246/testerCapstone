package com.example.testercapstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.EditText;

import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileWriter;
import java.io.PrintWriter;
import android.content.Context;
import android.widget.TextView;


public class metrics extends AppCompatActivity {
    Button goButton;
    EditText day;

    LineChart heartRateGraph;
    LineChart bloodOxygenGraph;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Context context;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics);
        username = Username.getInstance().getSharedVariable();

        day = findViewById(R.id.day);
        goButton = findViewById(R.id.goButton);
        bloodOxygenGraph = findViewById(R.id.bloodOxygenGraph);
        heartRateGraph = findViewById(R.id.heartRateGraph);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Welcome selected
        bottomNavigationView.setSelectedItemId(R.id.metrics);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.welcome)
                {
                    startActivity(new Intent(getApplicationContext(), WelcomePage.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                else if(item.getItemId() == R.id.userProfile)
                {
                    startActivity(new Intent(getApplicationContext(),UserProfile.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                else if(item.getItemId() == R.id.recordingPage)
                {
                    startActivity(new Intent(getApplicationContext(),RecordingPage.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                else if(item.getItemId() == R.id.metrics)
                {
                    return true;
                }
                else if(item.getItemId() == R.id.resourcesPage)
                {
                    startActivity(new Intent(getApplicationContext(), ResourcesPage.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String filename = username+"_data.csv";
                String filename = username+"_data.csv";
                if (day.getText().toString().equals("new")) {
                    // RECORD NEW DATA, GET STUFF FROM ARDUINO
                    startActivity(new Intent(getApplicationContext(),RecordingPage.class));

                } else {
                    // ACCESS DATA BASE FOR THE SPECIFIC USER FOR THE SPECIFIC DAY
                    int dayVal = Integer.parseInt(day.getText().toString());

                    ArrayList<Entry> entriesHeart = new ArrayList<>();
                    ArrayList<Entry> entriesBlood = new ArrayList<>();

                    File file = new File(getFilesDir(), filename);

                    try {
                        FileInputStream fis = new FileInputStream(file);
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader br = new BufferedReader(isr);

                        String line;
                        String heartRate;
                        String bloodOxy;
                        for (int i = 1; i < (dayVal*2); i++) {
                            line = br.readLine();
                        }

                        heartRate = br.readLine();

                        if (heartRate != null) {
                            String[] tokensHeart = heartRate.split(",");

                            int point = 0;

                            for (String value : tokensHeart){
                                int valueInt = Integer.parseInt(value);
                                entriesHeart.add(new Entry(point, valueInt));
                                point++;
                            }

                        }


                        bloodOxy = br.readLine();
                        if (bloodOxy != null) {
                            String[] tokensBlood = bloodOxy.split(",");
                            int point = 0;
                            for (String value : tokensBlood){
                                int valueInt = Integer.parseInt(value);
                                entriesBlood.add(new Entry(point, valueInt));
                                point++;
                            }
                        }

                        br.close();
                        isr.close();
                        fis.close();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    entriesHeart.add(new Entry(1, 74));
                    entriesHeart.add(new Entry(2, 75));
                    entriesHeart.add(new Entry(3, 79));
                    entriesHeart.add(new Entry(4, 77));
                    entriesHeart.add(new Entry(5, 69));
                    entriesHeart.add(new Entry(6, 75));
                    entriesHeart.add(new Entry(7, 74));
                    entriesHeart.add(new Entry(8, 74));


                    entriesBlood.add(new Entry(1, (float)0.94));
                    entriesBlood.add(new Entry(2, (float)0.92));
                    entriesBlood.add(new Entry(3, (float)0.98));
                    entriesBlood.add(new Entry(4, (float)0.94));
                    entriesBlood.add(new Entry(5, (float)0.93));
                    entriesBlood.add(new Entry(6, (float)0.91));
                    entriesBlood.add(new Entry(7, (float)0.95));
                    entriesBlood.add(new Entry(8, (float)0.94));





                    LineDataSet dataSetHeart = new LineDataSet(entriesHeart, "Label");
                    LineData lineDataHeart = new LineData(dataSetHeart);

                    LineDataSet dataSetBlood = new LineDataSet(entriesBlood, "Label");
                    LineData lineDataBlood = new LineData(dataSetBlood);

                    bloodOxygenGraph.setData(lineDataBlood);
                    bloodOxygenGraph.invalidate();

                    heartRateGraph.setData(lineDataHeart);
                    heartRateGraph.invalidate();
                }
            }
        });
    }

    private void writeUserDataToCSV(String username) {
        List<String> userDataList = new ArrayList<>();

        userDataList.add("69,75,70,73,75,75,74,69");
        userDataList.add("0.9,0.99,0.98,0.96,0.95");
        userDataList.add("75,74,74,71,73,75,72,73,74");
        userDataList.add("0.92,0.93,0.94,0.92,0.92,0.90,0.95,0.94");
        userDataList.add("75,75,73,79,70");
        userDataList.add("0.90,0.94,0.92,0.96,0.97");

        String filename = username;
       // SignUpHelper.writeToCSV(this, filename, userDataList);
    }

}