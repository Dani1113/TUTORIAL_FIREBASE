package com.example.tutorialfirebase.Tareas.TareasProductoPublicado;

import com.example.tutorialfirebase.Clases.ProductosPublicados;
import com.example.tutorialfirebase.Modelos.ProductosPublicadosDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerProductoPublicadoPorEmpresa implements Callable<ArrayList<ProductosPublicados>> {
    private ArrayList<ProductosPublicados> productosPublicadosDevueltos = null;
    private int página;
    private String cod_empr;

    public TareaObtenerProductoPublicadoPorEmpresa(int página, String cod_empr) {
        this.página = página;
        this.cod_empr = cod_empr;
    }

    @Override
    public ArrayList<ProductosPublicados> call() throws Exception {
        productosPublicadosDevueltos = ProductosPublicadosDB.obtenerProductosPublicadosPorEmpresa(página, cod_empr);
        return productosPublicadosDevueltos;
    }
}
