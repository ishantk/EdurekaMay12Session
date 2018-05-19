package co.edureka.edurekamay12session;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

public class FragmentDemoActivity extends AppCompatActivity {


    UpperFragment upperFragment;
    LowerFragment lowerFragment;

    void initViews(){

        upperFragment = new UpperFragment();
        lowerFragment = new LowerFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.upperFrame,upperFragment).commit();
        fragmentManager.beginTransaction().add(R.id.lowerFrame,lowerFragment).commit();

        //fragmentManager.beginTransaction().remove()
        //fragmentManager.beginTransaction().replace()


    }

    void showNotification(){

        //1.
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        //2.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("myChannelId","MyChannel",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        //3.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("This is Title");
        builder.setContentText("This is Text: "+new Date());
        builder.setSmallIcon(R.drawable.app_icon);
        builder.setChannelId("myChannelId");

        // Launch Activity on click of Notification
        Intent intent = new Intent(FragmentDemoActivity.this,ActivityOne.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,201,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        builder.setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle("This is Big Title"));
        builder.addAction(android.R.drawable.ic_menu_add,"Add",pendingIntent);
        builder.addAction(android.R.drawable.ic_menu_delete,"Delete",pendingIntent);

        // Requires Vibrate Permission in manifest
        builder.setDefaults(Notification.DEFAULT_ALL);// Vibration, Sound and Lights



        //4.
        Notification notification = builder.build();

        //5.
        notificationManager.notify(101,notification);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);
        initViews();
        showNotification();
    }
}
