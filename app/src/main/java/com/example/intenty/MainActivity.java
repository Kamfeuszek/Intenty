package com.example.intenty;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class MainActivity extends AppCompatActivity {

    Button buttonNavigate;
    TextView imie;
    Button notificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonNavigate = findViewById(R.id.buttonNavigate);
        imie = findViewById(R.id.imie);
        notificationButton = findViewById(R.id.notificationButton);
        buttonNavigate.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("message", "Witaj z MainActivity!");
            startActivity(intent);
        });
        String message = getIntent().getStringExtra("imie");
        imie.setText(message);
        notificationButton.setOnClickListener(v -> {
            sendNotification();
        });
    }
    private void sendNotification() {
        Intent intent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Nowe powiadomienie")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Lorem ipsum"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(1, builder.build());
    }
}