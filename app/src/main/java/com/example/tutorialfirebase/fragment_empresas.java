package com.example.tutorialfirebase;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tutorialfirebase.Clases.*;
import com.example.tutorialfirebase.Controladores.*;
import com.example.tutorialfirebase.utilidades.PaginationListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_empresas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_empresas extends Fragment {
    private static final int PETICION1 = 1;
    private RecyclerView mRecyclerView;
    private ListaEmpresasAdapter mAdapter;
    private ArrayList<Empresa> empresas;
    private int pagina_actual;
    private int total_registros;
    private int total_paginas;
    private int num_columnas_landscape;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista =  inflater.inflate(R.layout.fragment_empresas, container, false);
        Log.i("sql", String.valueOf(total_registros));
        total_registros = EmpresaController.obtenerCantidadEmpresas();
        Log.i("sql", String.valueOf(total_registros));
        Log.i("sql", "total registros -> " + String.valueOf(total_registros));

        total_paginas = (total_registros / ConfiguracionesGenerales.ELEMENTOS_POR_PAGINA) +  1;
        Log.i("sql", "total paginas -> " + String.valueOf(total_paginas));

        pagina_actual=0;
        num_columnas_landscape = 2;
        empresas = EmpresaController.obtenerEmpresas(pagina_actual);

        pagina_actual++;
        if(empresas != null) {
            Log.i("sql", "pagina actual -> " + String.valueOf(pagina_actual));
            Log.i("sql", "empresas leidas -> " + String.valueOf(this.empresas.size()));
            mRecyclerView = vista.findViewById(R.id.rv_empresas);
            mAdapter = new ListaEmpresasAdapter(getActivity(), empresas);
            mRecyclerView.setAdapter(mAdapter);
            int orientation = 1;
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), ConfiguracionesGenerales.LANDSCAPE_NUM_COLUMNAS));
            }

            // paginacion
            mRecyclerView.addOnScrollListener(new PaginationListener((LinearLayoutManager) mRecyclerView.getLayoutManager()) {
                private int empresas_leidas = 0;
                @Override
                protected void loadMoreItems() {

                    int total_registros_leidos = mRecyclerView.getLayoutManager().getItemCount();
                    if (total_registros_leidos < total_registros) {
                        ArrayList<Empresa> nuevasEmpresas = EmpresaController.obtenerEmpresas(pagina_actual);
                        empresas_leidas = nuevasEmpresas.size();
                        pagina_actual++;

                        Boolean resultado = mRecyclerView.post(new Runnable()
                        {
                            @Override
                            public void run() {
                                ListaEmpresasAdapter mAdapter1 =(ListaEmpresasAdapter) mRecyclerView.getAdapter();
                                ArrayList<Empresa> empresas_rv = mAdapter1.getListaEmpresas();
                                empresas_rv.addAll(nuevasEmpresas);
                                mRecyclerView.getAdapter().notifyDataSetChanged();
                            }});
                        Log.i("sql", "siguiente pagina -> " + String.valueOf(pagina_actual));
                        Log.i("sql", "total registros -> " + String.valueOf(total_registros));
                        Log.i("sql", "total registros leidos -> " + String.valueOf(total_registros_leidos));
                        Log.i("sql", "empresas leidas -> " + String.valueOf(this.empresas_leidas));

                    }
                    else{
                        empresas_leidas = 0;
                    }
                }
                @Override
                public boolean isLastPage() {
                    if(pagina_actual > total_paginas -1 ) {
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            });
        }
        else{
            mostrarToast("no se pudo establecer la conexion con la base de datos");
            Log.i("sql", "error en el sql");
        }
        return vista;
}
    private void mostrarToast(String texto) {
        Toast.makeText(getActivity(),texto, Toast.LENGTH_SHORT).show();
    }
}