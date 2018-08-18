package com.yevzor.lutsak.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int NOTIFICATION_ID = 1;
    private static final int REQUEST_CODE = 0;

    private Button mNotificationButton;
    private Button mCancelNotificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "--> onCreate");

        mNotificationButton = findViewById(R.id.notification_button);
        mCancelNotificationButton = findViewById(R.id.cancel_notification);
        mNotificationButton.setOnClickListener(this);
        mCancelNotificationButton.setOnClickListener(this);

        Log.d(TAG, "<-- onCreate");

    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick");

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        switch (view.getId()){
            case R.id.notification_button:
                Log.d(TAG, "Notification showed");

                Intent intent = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent =
                        PendingIntent.getActivity(
                                this,
                                REQUEST_CODE,
                                intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Notification")
                                .setContentIntent(pendingIntent)
                                .setContentText("It's example of notification text");

                Notification notification = builder.build();

                if (notificationManager != null) {
                    notificationManager.notify(NOTIFICATION_ID, notification);
                }
                break;
            case R.id.cancel_notification:
                Log.d(TAG, "Notification cancelled");

                if (notificationManager != null) {
                    notificationManager.cancel(NOTIFICATION_ID);
                }
                break;
        }


    }
}
