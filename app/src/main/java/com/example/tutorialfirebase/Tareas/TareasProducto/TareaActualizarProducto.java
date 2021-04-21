package com.example.tutorialfirebase.Tareas.TareasProducto;

import com.example.tutorialfirebase.Clases.Producto;
import com.example.tutorialfirebase.Modelos.ModaDB;
import com.example.tutorialfirebase.Modelos.ProductoDB;

import java.util.concurrent.Callable;

public class TareaActualizarProducto implements Callable<Boolean> {
    private Producto p;

    public TareaActualizarProducto(Producto p) {
        this.p = p;
    }

    @Override
    public Boolean call() throws Exception {
        boolean actualizadoOk = ProductoDB.actualizarProducto(p);
        return actualizadoOk;
    }
}
