package services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class Notify {
    Context context;
    public static final String CHANNEL_ONE = "Channel1";
    public static final String CHANNEL_TWO = "Channel2";

    public Notify(Context context) {
        this.context = context;
    }

    public void createChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(CHANNEL_ONE,
                    "Channel1", NotificationManager.IMPORTANCE_HIGH);

            NotificationChannel channel2 = new NotificationChannel(CHANNEL_TWO,
                    "Channel2", NotificationManager.IMPORTANCE_LOW);

            NotificationManager manager = context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }

}
