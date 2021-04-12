package com.example.tutorialfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class PerfilActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private TextView txtPCuenta;
    private TextView txtPActivar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        txtPCuenta = (TextView) findViewById(R.id.txtPCuenta);
        txtPActivar = (TextView) findViewById(R.id.txtPActivar);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser usuario = firebaseAuth.getCurrentUser();
                if(usuario == null){
                    Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    public void cerrarSesión(View view) {
        firebaseAuth.signOut();
        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void entrarAlChat(View view) {
        FirebaseUser usuario = firebaseAuth.getCurrentUser();
        if (!usuario.isEmailVerified()) {
            txtPCuenta.setVisibility(View.VISIBLE);
            txtPActivar.setVisibility(View.VISIBLE);
        } else {
            txtPCuenta.setVisibility(View.GONE);
            txtPActivar.setVisibility(View.GONE);
            Intent intent = new Intent(PerfilActivity.this, ChatActivity.class);
            startActivity(intent);
        }
    }

    public void activarCuenta(View view) {
        Toast.makeText(this, "Revisa tu email", Toast.LENGTH_SHORT).show();
        FirebaseUser usuario = firebaseAuth.getCurrentUser();
        usuario.sendEmailVerification();
        firebaseAuth.signOut();
    }
}