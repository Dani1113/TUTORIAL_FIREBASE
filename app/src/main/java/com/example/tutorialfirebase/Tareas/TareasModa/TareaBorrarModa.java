package com.example.tutorialfirebase.Tareas.TareasModa;

import com.example.tutorialfirebase.Clases.Moda;
import com.example.tutorialfirebase.Modelos.ModaDB;

import java.util.concurrent.Callable;

public class TareaBorrarModa implements Callable<Boolean> {
    private Moda m;

    public TareaBorrarModa(Moda m) {
        this.m =m ;
    }

    @Override
    public Boolean call() throws Exception {
        boolean borradoOk = ModaDB.borrarModa(m);
        return borradoOk;
    }
}
