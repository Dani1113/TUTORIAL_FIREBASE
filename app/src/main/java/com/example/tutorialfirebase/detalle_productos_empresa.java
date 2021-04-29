package com.example.tutorialfirebase;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tutorialfirebase.Clases.Empresa;
import com.example.tutorialfirebase.Clases.EmpresaViewHolder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link detalle_productos_empresa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detalle_productos_empresa extends Fragment {

    TextView txt_detalles_productos_empresa = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=  inflater.inflate(R.layout.fragment_detalle_productos_empresa, container, false);
        Empresa e1 = (Empresa)getArguments().getSerializable(EmpresaViewHolder.EXTRA_OBJETO_EMPRESA);
        txt_detalles_productos_empresa = (TextView)vista.findViewById(R.id.txt_detalle_clave_empresa);
        txt_detalles_productos_empresa.setText(e1.getCod_empresa());

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
        return vista;
    }
}