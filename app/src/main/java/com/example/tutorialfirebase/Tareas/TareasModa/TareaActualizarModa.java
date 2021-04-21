package com.example.tutorialfirebase.Tareas.TareasModa;

import com.example.tutorialfirebase.Clases.Moda;
import com.example.tutorialfirebase.Modelos.ModaDB;

import java.util.concurrent.Callable;

public class TareaActualizarModa implements Callable<Boolean> {
    private Moda m;

    public TareaActualizarModa(Moda m) {
        this.m = m;
    }

    @Override
    public Boolean call() throws Exception {
        boolean actualizadoOk = ModaDB.actualizarModa(m);
        return actualizadoOk;
    }
}
