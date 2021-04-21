package com.example.tutorialfirebase.Tareas.TareasProducto;

import com.example.tutorialfirebase.Clases.Producto;
import com.example.tutorialfirebase.Modelos.ProductoDB;

import java.util.concurrent.Callable;

public class TareaBorrarProducto implements Callable<Boolean> {
    private Producto p;

    public TareaBorrarProducto(Producto p) {
        this.p = p;
    }

    @Override
    public Boolean call() throws Exception {
        boolean borradoOk = ProductoDB.borrarProducto(p);
        return borradoOk;
    }
}
