package com.example.tutorialfirebase.Tareas.TareasModa;

import com.example.tutorialfirebase.Clases.Moda;
import com.example.tutorialfirebase.Modelos.ModaDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaBuscarModa implements Callable<ArrayList<Moda>> {
    private String talla = null;
    private ArrayList<Moda> modaEncontrada = null;

    public TareaBuscarModa(String talla) {
        this.talla = talla;
    }

    @Override
    public ArrayList<Moda> call() throws Exception {
        modaEncontrada = ModaDB.buscarModa(talla);
        return modaEncontrada;
    }
}
