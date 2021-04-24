package com.example.tutorialfirebase.Tareas.TareasModa;

import com.example.tutorialfirebase.Modelos.ModaDB;

public class TareaCantidadModa implements java.util.concurrent.Callable<Integer> {
    private int cantidadModa = 0;

    @Override
    public Integer call() throws Exception {
        cantidadModa = ModaDB.obtenerCantidadModa();
        return cantidadModa;
    }
}
