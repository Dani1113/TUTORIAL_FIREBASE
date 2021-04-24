package com.example.tutorialfirebase.Tareas.TareasProductoPublicado;

import com.example.tutorialfirebase.Modelos.ProductosPublicadosDB;

public class TareaCantidadProductoPublicado implements java.util.concurrent.Callable<Integer> {
    private int cantidadProductosPublicados = 0;

    @Override
    public Integer call() throws Exception {
        cantidadProductosPublicados = ProductosPublicadosDB.obtenerCantidadProductosPublicados();
        return cantidadProductosPublicados;
    }
}
