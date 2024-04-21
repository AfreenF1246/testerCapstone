package com.example.testercapstone;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import java.util.Random;
public class NotificationFrame {
    private Context mContext;
    private String[] notifications = new String[]{"Take deep breaths!", "Take a 15 minute walk!", "Don't be afraid to talk to someone!",
            "Take a 15 minute break!", "Get creative: try out a new craft!", "Write what's on your mind into paper!",
            "Watch a funny show or video!", "Try meditation!", "Write down some things you're grateful for!",
            "It's okay to take breaks!"};

    public NotificationFrame(Context context) {
        mContext = context;
    }

    public void notif(Boolean heartRate, Boolean bloodOxy){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, "channel_id");
        builder.setSmallIcon(R.drawable.baseline_notifications_active_24);

        if(heartRate && bloodOxy){
            builder.setContentTitle("Your heart rate and blood oxygen level indicates a potential stress reaction!");
        }
        else if(heartRate){
            builder.setContentTitle("Your heart rate indicates a potential stress reaction!");
        }
        else{
            builder.setContentTitle("Your blood oxygen level indicates a potential stress reaction!");
        }

        Random rand = new Random();
        int randVal = rand.nextInt(10);
        builder.setContentText(notifications[randVal]);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);
        //notificationManager.notify(0, builder.build());
    }

}
