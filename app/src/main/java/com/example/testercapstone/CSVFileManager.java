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
            writer.write(userData);

            // Close the writer
            writer.close();

            Log.d(TAG, "User data written to CSV file: " + fileName);
        } catch (IOException e) {
            Log.e(TAG, "Error writing to CSV file: " + fileName + " - " + e.getMessage());
            e.printStackTrace();
        }
    }
}

