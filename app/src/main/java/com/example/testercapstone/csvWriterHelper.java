package com.example.testercapstone;
import android.content.Context;
import android.util.Log;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class csvWriterHelper{

    public static void writeToCSV(Context context, String userData, String filename) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_APPEND);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);

            // Write the user data to the file
            writer.write(userData);

            // Close the writer
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}