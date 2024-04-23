package com.example.testercapstone;
import android.content.Context;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SignUpHelper{
    private static final String TAG = "SignupHelper";

    public static void writeToCSV(Context context, String userData) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput("users.csv", Context.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);

            // Write the user data to the file
            writer.write(userData);

            // Close the writer
            writer.close();

            Log.d(TAG, "User data written to CSV file");
        } catch (IOException e) {
            Log.e(TAG, "Error writing to CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}