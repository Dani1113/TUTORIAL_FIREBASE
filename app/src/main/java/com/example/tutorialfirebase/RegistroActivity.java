package com.example.tutorialfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
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
                firebaseAuth.signOut();
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);
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
        validaciónRegistroUsuario();
    }

    private void validaciónRegistroUsuario() {
        String usuario = String.valueOf(edtRUsuario.getText());
        String contraseña = String.valueOf(edtRContraseña.getText());
        String confirmarContraseña = String.valueOf(edtRConfirmarContraseña.getText());

        if (usuario.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        } else if (!usuario.contains("@")) {
            edtRUsuario.setError("Formato de usuario incorrecto (debe incluir @)");
        } else if (contraseña.length() < 6) {
            edtRContraseña.setError("La contraseña debe de tener 6 caracteres o más");
        } else if (!contraseña.equalsIgnoreCase(confirmarContraseña)) {
            edtRConfirmarContraseña.setError("Las contraseñas no coinciden");
        } else {
            firebaseAuth.signInWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Log.i("Error", "Excepción " + task.getException().toString());
                        try{
                            throw task.getException();
                        }catch (FirebaseAuthInvalidUserException e){
                            crearUsuarioFirebase(usuario, contraseña);
                        } catch (FirebaseAuthInvalidCredentialsException e){
                            Toast.makeText(RegistroActivity.this, "Usuario ya registrado", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    private void crearUsuarioFirebase(String usuario, String contraseña) {
        firebaseAuth.createUserWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(RegistroActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(RegistroActivity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}