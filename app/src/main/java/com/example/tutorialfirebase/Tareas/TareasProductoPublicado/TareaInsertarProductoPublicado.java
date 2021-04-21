package com.example.tutorialfirebase.Tareas.TareasProductoPublicado;

import com.example.tutorialfirebase.Clases.ProductosPublicados;
import com.example.tutorialfirebase.Modelos.ProductosPublicadosDB;

import java.util.concurrent.Callable;

public class TareaInsertarProductoPublicado implements Callable<Boolean> {
    private ProductosPublicados pp;

    public TareaInsertarProductoPublicado(ProductosPublicados pp) {
        this.pp = pp;
    }

    @Override
    public Boolean call() throws Exception {
        boolean insertadoOk = ProductosPublicadosDB.insertarProductoPublicado(pp);
        return insertadoOk;
    }
}
