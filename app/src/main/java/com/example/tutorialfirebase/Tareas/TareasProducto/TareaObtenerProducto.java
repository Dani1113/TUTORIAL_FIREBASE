package com.example.tutorialfirebase.Tareas.TareasProducto;

import com.example.tutorialfirebase.Clases.Producto;
import com.example.tutorialfirebase.Modelos.ProductoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerProducto implements Callable<ArrayList<Producto>> {
    private ArrayList<Producto> productosDevueltos = null;
    private int página;

    public TareaObtenerProducto(int página) {
        this.página = página;
    }

    @Override
    public ArrayList<Producto> call() throws Exception {
        productosDevueltos = ProductoDB.obtenerProducto(página);
        return productosDevueltos;
    }
}
