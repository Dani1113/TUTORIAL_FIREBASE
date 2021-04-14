package com.example.tutorialfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BienvenidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
    }

    public void entrarALaApp(View view) {
        Intent intent = new Intent(BienvenidaActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    public void inciarSesión(View view) {
        Intent intent = new Intent(BienvenidaActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}