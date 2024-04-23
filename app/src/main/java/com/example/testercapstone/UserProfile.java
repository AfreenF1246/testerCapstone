package com.example.testercapstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserProfile extends AppCompatActivity {
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        username = Username.getInstance().getSharedVariable();

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Welcome selected
        bottomNavigationView.setSelectedItemId(R.id.userProfile);


        // fill in user data
        EditText firstName = findViewById(R.id.fname);
        EditText lastName = findViewById(R.id.lname);
        EditText date = findViewById(R.id.dob);
        EditText gender = findViewById(R.id.sex);
        EditText emailAdd = findViewById(R.id.email);
        EditText phoneNo = findViewById(R.id.phoneNumber);
        EditText usernameEdit = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        usernameEdit.setText(username);
        String filename = username+"_data.csv";

        File file = new File(getFilesDir(), filename);
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            if (line != null){
                String[] tokens = line.split(",");

                firstName.setText(tokens[0]);
                lastName.setText(tokens[1]);
                date.setText(tokens[2]);
                gender.setText(tokens[4]);
                emailAdd.setText(tokens[3]);
                phoneNo.setText(tokens[5]);
                password.setText(tokens[7]);

            }
            br.close();
            isr.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.welcome)
                {
                    startActivity(new Intent(getApplicationContext(),WelcomePage.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                else if(item.getItemId() == R.id.userProfile)
                {
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
                    startActivity(new Intent(getApplicationContext(),ResourcesPage.class));
                    overridePendingTransition(0,0);
                    return true;
                }
                return false;
            }
        });

    }

}