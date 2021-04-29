package com.example.tutorialfirebase;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tutorialfirebase.Clases.Empresa;
import com.example.tutorialfirebase.Clases.EmpresaViewHolder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link detalle_productos_empresa #newInstance} factory method to
 * create an instance of this fragment.
 */
public class detalle_productos_empresa extends Fragment {

    TextView txt_detalles_productos_empresa = null;
    Button btn_atras ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=  inflater.inflate(R.layout.fragment_producto_publicado, container, false);
        Empresa e1 = (Empresa)getArguments().getSerializable(EmpresaViewHolder.EXTRA_OBJETO_EMPRESA);
        txt_detalles_productos_empresa = (TextView)vista.findViewById(R.id.texto_empresas);
        btn_atras = (Button) vista.findViewById(R.id.btn_Atras);
        txt_detalles_productos_empresa.setText(e1.getCod_empresa());

        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = NavHostFragment.findNavController(detalle_productos_empresa.this);
                navController.popBackStack();
            }
        });


        return vista;
    }
}