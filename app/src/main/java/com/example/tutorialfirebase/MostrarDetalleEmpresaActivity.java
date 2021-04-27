package com.example.tutorialfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import  com.example.tutorialfirebase.Utilidades.ImagenesBlobBitmap;


import com.example.tutorialfirebase.Clases.*;

import static com.example.tutorialfirebase.Clases.EmpresaViewHolder.EXTRA_OBJETO_EMPRESA;

public class MostrarDetalleEmpresaActivity extends AppCompatActivity {


    TextView txt_detalle_cod_empresa = null;
    TextView txt_detalle_clave_empresa = null;
    TextView txt_detalle_datos_empresa= null;
    ImageView img_detalle_img_empresa= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalle_empresa);
        txt_detalle_cod_empresa = (TextView)findViewById(R.id.txt_detalle_cod_empresa);
        txt_detalle_clave_empresa = (TextView)findViewById(R.id.txt_detalle_clave_empresa);
        txt_detalle_datos_empresa = (TextView)findViewById(R.id.txt_detalle_datos_empresa);
        img_detalle_img_empresa = (ImageView)findViewById(R.id.img_detalle_empresa);
        Intent intent = getIntent();
        if (intent != null) {
            Empresa e = (Empresa) intent.getSerializableExtra(EXTRA_OBJETO_EMPRESA);
            txt_detalle_cod_empresa.setText("cod_empresa: " + String.valueOf(e.getCod_empresa()));
            txt_detalle_clave_empresa.setText("clave_empresa: " + e.getClave_empr());
            txt_detalle_datos_empresa.setText("datos_empresa: " + e.getDatos_empr());
            byte[] byteArray = intent.getByteArrayExtra(EmpresaViewHolder.EXTRA_IMAGEN_EMPRESA);
            if (byteArray != null) {
                img_detalle_img_empresa.setImageBitmap(ImagenesBlobBitmap.bytes_to_bitmap(byteArray));
            }
        }
    }

    public void atras_detalle(View view) {
        finish();
    }
    }
