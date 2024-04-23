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




public class metrics extends AppCompatActivity {
    Button goButton;
    EditText day;

    LineChart heartRateGraph;
    LineChart bloodOxygenGraph;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics);

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
                username = Username.getInstance().getSharedVariable();
                String filename = username+"_data.csv";

                if (day.getText().toString().equals("new")) {
                    // RECORD NEW DATA, GET STUFF FROM ARDUINO
                    startActivity(new Intent(getApplicationContext(),RecordingPage.class));

                } else {
                    // ACCESS DATA BASE FOR THE SPECIFIC USER FOR THE SPECIFIC DAY
                    int dayVal = Integer.parseInt(day.getText().toString());
                    File file = new File(getFilesDir(), filename);
                    String[] tokensHeart = new String[0];
                    String[] tokensBlood = new String[0];

                    try {
                        FileInputStream fis = new FileInputStream(file);
                        InputStreamReader isr = new InputStreamReader(fis);
                        BufferedReader br = new BufferedReader(isr);
                        String heartRate;
                        String bloodOxy;
                        for (int i = 0; i < (dayVal*2)+1; i++) {
                            br.readLine();
                        }

                        heartRate = br.readLine();
                        bloodOxy = br.readLine();

                        if (heartRate != null) {
                            tokensHeart = heartRate.split(",");
                        }
                        if (bloodOxy != null) {
                            tokensBlood = bloodOxy.split(",");
                        }
                        br.close();
                        isr.close();
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ArrayList<Entry> entriesHeart = new ArrayList<>();
                    ArrayList<Entry> entriesBlood = new ArrayList<>();

                    int point = 0;
                    for (String value : tokensHeart){
                        int valueInt = Integer.parseInt(value);
                        entriesHeart.add(new Entry(point, valueInt));
                        point++;
                    }

                    point = 0;
                    for (String value : tokensBlood){
                        int valueInt = Integer.parseInt(value);
                        entriesBlood.add(new Entry(point, valueInt));
                        point++;
                    }


                    LineDataSet dataSetHeart = new LineDataSet(entriesHeart, "Label");
                    LineData lineDataHeart = new LineData(dataSetHeart);

                    LineDataSet dataSetBlood = new LineDataSet(entriesBlood, "Label");
                    LineData lineDataBlood = new LineData(dataSetBlood);

                    bloodOxygenGraph.setData(lineDataHeart);
                    bloodOxygenGraph.invalidate();

                    heartRateGraph.setData(lineDataBlood);
                    heartRateGraph.invalidate();
                }
            }
        });
    }
}