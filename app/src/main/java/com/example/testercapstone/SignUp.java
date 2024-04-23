package com.example.testercapstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    EditText firstName, lastName, DOB, email, sex, phoneNumber, username, password;
    Button Submit;
    //DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //DB = new DBHelper(this);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        DOB = findViewById(R.id.DOB);
        email = findViewById(R.id.email);
        sex = findViewById(R.id.sex);
        phoneNumber = findViewById(R.id.phoneNumber);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Submit = findViewById(R.id.Submit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userFirstName = firstName.getText().toString();
                String userLastName = lastName.getText().toString();
                String userDOB = DOB.getText().toString();
                String userEmail = email.getText().toString();
                String userSex = sex.getText().toString();
                String userPhoneNumber = phoneNumber.getText().toString();
                String userUsername = username.getText().toString();
                String userPassword = password.getText().toString();

                if (userUsername.equals("") || userPassword.equals("")) {
                    Toast.makeText(SignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    writeUserDataToCSV(userFirstName, userLastName, userDOB, userEmail, userSex, userPhoneNumber, userUsername, userPassword);

                    String uniqueFileName = generateUniqueFileName(userUsername); // You need to implement this method
                    String userData = userFirstName + "," + userLastName + "," + userDOB + "," + userEmail + "," + userSex + "," + userPhoneNumber + "," + userUsername + "," + userPassword + "\n";
                    CSVFileManager.writeUserDataToCSV(getApplicationContext(), uniqueFileName, userData);
                    Username.getInstance().setSharedVariable(userUsername);

                    Intent intent = new Intent(getApplicationContext(), WelcomePage.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void writeUserDataToCSV(String userFirstName, String userLastName, String userDOB, String userEmail, String userSex, String userPhoneNumber, String userUsername, String userPassword) {
        // Format the user data as a CSV row
        String userData = userUsername + "," + userPassword + "," + userFirstName + "," + userLastName + "," + userDOB + "," + userEmail + "," + userSex + "," + userPhoneNumber + "," + "\n";

        // Write the user data to the CSV file
        SignUpHelper.writeToCSV(this, userData);
    }

    private String generateUniqueFileName(String username) {
        return username + "_data.csv"; // Example: "john_doe_data.csv"
    }
}