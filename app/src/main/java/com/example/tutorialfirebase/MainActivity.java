package com.example.tutorialfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText edtLUsuario;
    private EditText edtLContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLUsuario = (EditText) findViewById(R.id.edtLUsuario);
        edtLContraseña = (EditText) findViewById(R.id.edtLContraseña);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser usuario = firebaseAuth.getCurrentUser();
                if(usuario != null){
                    if(!usuario.isEmailVerified()){
                        Toast.makeText(MainActivity.this, "E-mail no verificado" + usuario.getEmail(), Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Navegando al activity del chat", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    public void registrarUsuario(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    public void inciarSesión(View view) {
        String usuario = String.valueOf(edtLUsuario.getText());
        String contraseña = String.valueOf(edtLContraseña.getText());

        firebaseAuth.signInWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void inciarSesiónGoogle(View view) {
        Toast.makeText(this, "Opción no disponible por el momento", Toast.LENGTH_LONG).show();
    }

    public void inciarSesiónFacebook(View view) {
        Toast.makeText(this, "Opción no disponible por el momento", Toast.LENGTH_LONG).show();
    }
}