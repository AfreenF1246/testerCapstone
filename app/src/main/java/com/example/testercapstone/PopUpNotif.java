package com.example.testercapstone;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.Random;

public class PopUpNotif {
    private Context mContext;
    private String[] notifications = new String[]{"Take deep breaths!", "Take a 15 minute walk!", "Don't be afraid to talk to someone!",
            "Take a 15 minute break!", "Get creative: try out a new craft!", "Write what's on your mind into paper!",
            "Watch a funny show or video!", "Try meditation!", "Write down some things you're grateful for!",
            "It's okay to take breaks!"};

    public PopUpNotif(Context context) {
        mContext = context;
    }

    public void popUp(Boolean heartRate, Boolean bloodOxy){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        if(heartRate && bloodOxy){
            builder.setTitle("Your heart rate and blood oxygen level indicates a potential stress reaction!");
        }
        else if(heartRate){
            builder.setTitle("Your heart rate indicates a potential stress reaction!");
        }
        else{
            builder.setTitle("Your blood oxygen level indicates a potential stress reaction!");
        }

        Random rand = new Random();
        int randVal = rand.nextInt(10);
        builder.setMessage(notifications[randVal]);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
