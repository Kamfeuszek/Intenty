package com.example.intenty;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button buttonNavigate;
    TextView imie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonNavigate = findViewById(R.id.buttonNavigate);
        imie = findViewById(R.id.imie);
        buttonNavigate.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("message", "Witaj z MainActivity!");
            startActivity(intent);
        });
        String message = getIntent().getStringExtra("imie");
        imie.setText(message);
    }
}