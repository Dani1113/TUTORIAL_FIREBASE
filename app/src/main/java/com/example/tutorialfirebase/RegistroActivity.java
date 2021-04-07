package com.example.tutorialfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText edtRUsuario;
    private EditText edtRContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtRUsuario = (EditText) findViewById(R.id.edtRUsuario);
        edtRContraseña = (EditText) findViewById(R.id.edtRContraseña);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser usuario = firebaseAuth.getCurrentUser();
                if(usuario != null){
                    Toast.makeText(RegistroActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegistroActivity.this, "Error al crear el usuario", Toast.LENGTH_SHORT).show();
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
        String usuario = String.valueOf(edtRUsuario.getText());
        String contraseña = String.valueOf(edtRContraseña.getText());

        firebaseAuth.createUserWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(RegistroActivity.this, "Error al crear el usuario", Toast.LENGTH_LONG).show();
                }else {
                    FirebaseUser usuario = firebaseAuth.getCurrentUser();
                    usuario.sendEmailVerification();
                }
            }
        });
    }
}