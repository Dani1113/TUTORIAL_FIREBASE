package com.example.tutorialfirebase.Tareas.TareasModa;

import com.example.tutorialfirebase.Clases.Moda;
import com.example.tutorialfirebase.Modelos.ModaDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerModa implements Callable<ArrayList<Moda>> {
    private ArrayList<Moda> modaDevuelta = null;
    private int página;

    public TareaObtenerModa(int página) {
        this.página = página;
    }

    @Override
    public ArrayList<Moda> call() throws Exception {
        modaDevuelta = ModaDB.obtenerModa(página);
        return modaDevuelta;
    }
}
