package com.example.tutorialfirebase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link detalle_productos_empresa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detalle_productos_empresa extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=  inflater.inflate(R.layout.fragment_detalle_productos_empresa, container, false);

        return vista;
    }
}