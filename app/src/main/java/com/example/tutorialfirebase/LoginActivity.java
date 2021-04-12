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

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText edtLUsuario;
    private EditText edtLContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLUsuario = (EditText) findViewById(R.id.edtLUsuario);
        edtLContraseña = (EditText) findViewById(R.id.edtLContraseña);

        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser usuario = firebaseAuth.getCurrentUser();
            if(usuario != null){
                edtLUsuario.setText("");
                edtLContraseña.setText("");
                Toast.makeText(LoginActivity.this, "Sesión iniciada", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, PerfilActivity.class);
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
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

    public void inciarSesión(View view) {
        validacionIncioSesión();
    }

    public void inciarSesiónGoogle(View view) {
        Toast.makeText(this, "Opción no disponible por el momento", Toast.LENGTH_LONG).show();
    }

    public void inciarSesiónFacebook(View view) {
        Toast.makeText(this, "Opción no disponible por el momento", Toast.LENGTH_LONG).show();
    }

    public void recordarContraseña(View view) {
        Intent intent = new Intent(LoginActivity.this, RecuperarPasswordActivity.class);
        startActivity(intent);
    }

    private void validacionIncioSesión() {
        String usuario = String.valueOf(edtLUsuario.getText());
        String contraseña = String.valueOf(edtLContraseña.getText());

        if (usuario.isEmpty() && contraseña.isEmpty()){
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        }else if (!usuario.contains("@")){
            edtLUsuario.setError("Formato de usuario incorrecto (debe incluir @)");
        }else if (contraseña.length() < 6){
            edtLContraseña.setError("La contraseña debe de tener 6 caracteres o más");
        }else{
            firebaseAuth.signInWithEmailAndPassword(usuario, contraseña).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Log.i("Error", "Excepción " + task.getException().toString());
                        try{
                            throw task.getException();
                        } catch (FirebaseAuthInvalidUserException e){
                            Toast.makeText(LoginActivity.this, "Usuario no encontrado", Toast.LENGTH_LONG).show();
                        } catch (FirebaseAuthInvalidCredentialsException e){
                            Toast.makeText(LoginActivity.this, "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}