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

public class RegistroActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText edtRUsuario;
    private EditText edtRContraseña;
    private EditText edtRConfirmarContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtRUsuario = (EditText) findViewById(R.id.edtRUsuario);
        edtRContraseña = (EditText) findViewById(R.id.edtRContraseña);
        edtRConfirmarContraseña = (EditText) findViewById(R.id.edtRConfirmarContraseña);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser usuario = firebaseAuth.getCurrentUser();
            if(usuario != null){
                Toast.makeText(RegistroActivity.this, "Usuario registrado, sesión iniciada", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistroActivity.this, PerfilActivity.class);
                startActivity(intent);
                finish();
            }
        }};
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
        String confirmarContraseña = String.valueOf(edtRConfirmarContraseña.getText());

        validaciónRegistroUsuario(usuario, contraseña, confirmarContraseña);
    }

    private void validaciónRegistroUsuario(String usuario, String contraseña, String confirmarContraseña) {
        if(!usuario.isEmpty() && !contraseña.isEmpty() && !confirmarContraseña.isEmpty()){
            if(usuario.contains("@")){
                if(contraseña.length() >= 6 && confirmarContraseña.length() >= 6){
                    if(contraseña.equalsIgnoreCase(confirmarContraseña)){
                        firebaseAuth.createUserWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(RegistroActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(RegistroActivity.this, "Se le ha enviado un email para verificar su correo electrónico", Toast.LENGTH_SHORT).show();
                                    FirebaseUser fUsuario = firebaseAuth.getCurrentUser();
                                    fUsuario.sendEmailVerification();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "La contraseña debe de tener 6 caracteres o más", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "Formato de usuario incorrecto (debe incluir @)", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}