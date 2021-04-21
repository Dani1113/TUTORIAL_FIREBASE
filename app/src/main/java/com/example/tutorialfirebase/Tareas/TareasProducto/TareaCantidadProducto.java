package com.example.tutorialfirebase.Tareas.TareasProducto;

import com.example.tutorialfirebase.Modelos.ProductoDB;

public class TareaCantidadProducto implements java.util.concurrent.Callable<Integer> {
    private int candtidadProducto = 0;

    @Override
    public Integer call() throws Exception {
        candtidadProducto = ProductoDB.obtenerCantidadProductos();
        return candtidadProducto;
    }
}
