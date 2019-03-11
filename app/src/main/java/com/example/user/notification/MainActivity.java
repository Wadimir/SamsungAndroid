package com.example.user.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_ID = 1;
    public String title = "", content = "";
    public EditText titleEditText, contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView dialogTv = findViewById(R.id.send);


        dialogTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }


    //DIALOG
    private void showDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_layout,
                (ViewGroup) findViewById(R.id.dialog));
        titleEditText = layout.findViewById(R.id.title);
        contentEditText = layout.findViewById(R.id.content);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setView(layout);


        dialog.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("Выслать", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                title = String.valueOf(titleEditText.getText());
                content = String.valueOf(contentEditText.getText());
                sendNotification();
            }
        });
        dialog.show();
    }


    //NOTIFICATION
    public void sendNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "not_channel_id";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // creating a notification channel
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Description");
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        // create notification
        NotificationCompat.Builder notificationBuilder
                = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setTicker("JamesHetfield")
                .setContentTitle(title)
                .setContentText(content)
                .setContentInfo("Information");

        // push notification
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());

    }

}
