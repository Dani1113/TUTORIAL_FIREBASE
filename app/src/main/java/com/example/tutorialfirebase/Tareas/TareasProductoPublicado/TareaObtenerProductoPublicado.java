package com.example.tutorialfirebase.Tareas.TareasProductoPublicado;

import com.example.tutorialfirebase.Clases.ProductosPublicados;
import com.example.tutorialfirebase.Modelos.ProductosPublicadosDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerProductoPublicado implements Callable<ArrayList<ProductosPublicados>> {
    private ArrayList<ProductosPublicados> productosPublicadosDevueltos = null;
    private int página;

    public TareaObtenerProductoPublicado(int página) {
        this.página = página;
    }

    @Override
    public ArrayList<ProductosPublicados> call() throws Exception {
        productosPublicadosDevueltos = ProductosPublicadosDB.obtenerProductosPublicados(página);
        return productosPublicadosDevueltos;
    }
}