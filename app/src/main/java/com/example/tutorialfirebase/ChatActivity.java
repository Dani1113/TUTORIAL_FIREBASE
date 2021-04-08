package com.example.tutorialfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChatActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private TextView txtCuenta;
    private TextView txtActivar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        txtCuenta = (TextView) findViewById(R.id.txtCuenta);
        txtActivar = (TextView) findViewById(R.id.txtActivar);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void cerrarSesión(View view) {
        firebaseAuth.signOut();
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ChatActivity.this, MainActivity.class);
        finish();
    }

    public void entrarAlChat(View view) {
        FirebaseUser usuario = firebaseAuth.getCurrentUser();
        if (!usuario.isEmailVerified()) {
            txtCuenta.setVisibility(View.VISIBLE);
            txtActivar.setVisibility(View.VISIBLE);
        } else {
            txtCuenta.setVisibility(View.INVISIBLE);
            txtActivar.setVisibility(View.INVISIBLE);
        }
    }

    public void activarCuenta(View view) {
        Toast.makeText(this, "Revisa tu email", Toast.LENGTH_SHORT).show();
        FirebaseUser usuario = firebaseAuth.getCurrentUser();
        usuario.sendEmailVerification();
        Intent intent = new Intent(ChatActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}