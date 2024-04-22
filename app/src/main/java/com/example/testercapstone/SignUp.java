package com.example.testercapstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText firstName, lastName, DOB, email, sex, phoneNumber, username, password;
    Button Submit;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        DOB = (EditText) findViewById(R.id.DOB);
        email = (EditText) findViewById(R.id.email);
        sex = (EditText) findViewById(R.id.sex);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Submit = (Button) findViewById(R.id.Submit);

        DB = new DBHelper(this);
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

                if(userUsername.equals("")||userPassword.equals("")) {
                    Toast.makeText(SignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }else{
                        Boolean checkuser = DB.checkusername(userUsername);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(userFirstName, userLastName, userDOB, userEmail, userSex, userPhoneNumber,  userUsername, userPassword);
                            if(insert==true){
                                Toast.makeText(SignUp.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),WelcomePage.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SignUp.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        });


    }
    /////
}