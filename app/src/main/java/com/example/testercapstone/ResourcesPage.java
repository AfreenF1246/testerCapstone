package com.example.testercapstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ResourcesPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_page);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Welcome selected
        bottomNavigationView.setSelectedItemId(R.id.resourcesPage);

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
                    startActivity(new Intent(getApplicationContext(),metrics.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                else if(item.getItemId() == R.id.resourcesPage)
                {
                    return true;
                }
                return false;
            }
        });
    }
}