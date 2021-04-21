package com.example.tutorialfirebase.Tareas.TareasModa;

import com.example.tutorialfirebase.Clases.Moda;
import com.example.tutorialfirebase.Modelos.ModaDB;

import java.util.concurrent.Callable;

public class TareaInsertarModa implements Callable<Boolean> {
    private Moda m;

    public TareaInsertarModa(Moda m) {
        this.m = m;
    }

    @Override
    public Boolean call() throws Exception {
        boolean insertadoOk = ModaDB.insertarModa(m);
        return insertadoOk;
    }
}
