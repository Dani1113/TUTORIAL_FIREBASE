package com.example.tutorialfirebase.Tareas.TareasProductoPublicado;

import com.example.tutorialfirebase.Clases.Producto;
import com.example.tutorialfirebase.Clases.ProductosPublicados;
import com.example.tutorialfirebase.Modelos.ProductosPublicadosDB;

import java.util.concurrent.Callable;

public class TareaActualizarProductoPublicado implements Callable<Boolean> {
    private ProductosPublicados pp;

    public TareaActualizarProductoPublicado(ProductosPublicados pp) {
        this.pp = pp;
    }

    @Override
    public Boolean call() throws Exception {
        //boolean actualizadoOk = ProductosPublicadosDB.actualizarProductoPublicado(pp);
        return null;
    }
}
