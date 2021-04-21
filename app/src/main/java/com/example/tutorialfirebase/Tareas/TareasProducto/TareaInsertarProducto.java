package com.example.tutorialfirebase.Tareas.TareasProducto;

import com.example.tutorialfirebase.Clases.Producto;
import com.example.tutorialfirebase.Modelos.ProductoDB;

import java.util.concurrent.Callable;

public class TareaInsertarProducto implements Callable<Boolean> {
    private Producto p;

    public TareaInsertarProducto(Producto p) {
        this.p = p;
    }

    @Override
    public Boolean call() throws Exception {
        boolean insertadoOk = ProductoDB.insertarProducto(p);
        return insertadoOk;
    }
}
