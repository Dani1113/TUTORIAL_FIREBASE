package com.example.tutorialfirebase.Tareas.TareasProductoPublicado;

import com.example.tutorialfirebase.Clases.ProductosPublicados;
import com.example.tutorialfirebase.Modelos.ProductosPublicadosDB;

import java.util.concurrent.Callable;

public class TareaBorrarProductoPublicado implements Callable<Boolean> {
    private ProductosPublicados pp;

    public TareaBorrarProductoPublicado(ProductosPublicados pp) {
        this.pp = pp;
    }

    @Override
    public Boolean call() throws Exception {
        boolean borradoOk = ProductosPublicadosDB.borrarProductoPublicado(pp);
        return borradoOk;
    }
}
