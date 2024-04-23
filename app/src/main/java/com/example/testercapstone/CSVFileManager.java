package com.example.testercapstone;

import android.content.Context;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CSVFileManager {
    private static final String TAG = "CSVFileManager";

    public static void writeUserDataToCSV(Context context, String fileName, String userData) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);

            // Write the user data to the file
            writer.write(userData + "\n");
            writer.write("69,75,70,73,75,75,74,69\n");
            writer.write("0.9,0.99,0.98,0.96,0.95\n");
            writer.write("75,74,74,71,73,75,72,73,74\n");
            writer.write("0.92,0.93,0.94,0.92,0.92,0.90,0.95,0.94\n");
            writer.write("75,75,73,79,70\n");
            writer.write("0.90,0.94,0.92,0.96,0.97\n");


            // Close the writer
            writer.close();

            Log.d(TAG, "User data written to CSV file: " + fileName);
        } catch (IOException e) {
            Log.e(TAG, "Error writing to CSV file: " + fileName + " - " + e.getMessage());
            e.printStackTrace();
        }
    }
}

