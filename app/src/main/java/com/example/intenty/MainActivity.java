package com.example.intenty;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class MainActivity extends AppCompatActivity {
    Button notificationButton;
    Button notificationButton2;
    Button notificationButton3;
    public static final String CHANNEL_ID = "my_channel_id";
    private int ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationHelper.createNotificationChannels(this);
        notificationButton = findViewById(R.id.notificationButton);
        notificationButton2 = findViewById(R.id.notificationButton2);
        notificationButton3 = findViewById(R.id.notificationButton3);

        notificationButton.setOnClickListener(v -> {
            NotificationHelper.sendNotification(ID, NotificationHelper.CHANNEL_ID_LOW,this, this, "Tytuł", "Wiadomość", 1, R.drawable.notification, R.raw.dzwonek);
            ID++;
        });
        notificationButton2.setOnClickListener(v -> {
            NotificationHelper.sendNotification(ID, NotificationHelper.CHANNEL_ID_DEFAULT ,this, this, "Tytuł2", "Wiadomość2",2, R.drawable.notification, R.raw.dzwonek);
            ID++;
        });
        notificationButton3.setOnClickListener(v -> {
            NotificationHelper.sendNotification(ID, NotificationHelper.CHANNEL_ID_HIGH ,this, this, "Tytuł3", "Wiadomość3" ,3, R.drawable.notification, R.raw.dzwonek);
            ID++;
        });
    }
    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Kanał Powiadomień";
            String description = "Opis kanału powiadomień";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);

        }
    }
    public void sendNotification(Context context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if(context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }

        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Nowe powiadomienie")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Lorem ipsum"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(1, builder.build());
    }
}