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

import java.util.ArrayList;


public class metrics extends AppCompatActivity {
    Button goButton;
    EditText day;

    LineChart heartRateGraph;
    LineChart bloodOxygenGraph;

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
                if (day.getText().toString().equals("new")) {
                    // RECORD NEW DATA, GET STUFF FROM ARDUINO
                    startActivity(new Intent(getApplicationContext(),RecordingPage.class));

                } else {
                    // ACCESS DATA BASE FOR THE SPECIFIC USER FOR THE SPECIFIC DAY
                    ArrayList<Entry> entries = new ArrayList<>();
                    entries.add(new Entry(0, 4));
                    entries.add(new Entry(1, 8));
                    entries.add(new Entry(2, 6));
                    // Add more data points...

                    LineDataSet dataSet = new LineDataSet(entries, "Label");
                    LineData lineData = new LineData(dataSet);

                    bloodOxygenGraph.setData(lineData);
                    bloodOxygenGraph.invalidate();

                    heartRateGraph.setData(lineData);
                    heartRateGraph.invalidate();
                }
            }
        });
    }
}