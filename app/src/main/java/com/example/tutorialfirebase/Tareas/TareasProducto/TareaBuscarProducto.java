package com.example.tutorialfirebase.Tareas.TareasProducto;

import com.example.tutorialfirebase.Clases.Producto;
import com.example.tutorialfirebase.Modelos.ProductoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaBuscarProducto implements Callable<ArrayList<Producto>> {
    private String modelo = null;
    private ArrayList<Producto> productosEncontrados = null;

    public TareaBuscarProducto(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public ArrayList<Producto> call() throws Exception {
        productosEncontrados = ProductoDB.buscarProducto(modelo);
        return productosEncontrados;
    }
}
