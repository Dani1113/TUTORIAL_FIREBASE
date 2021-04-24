package com.example.tutorialfirebase.Tareas.TareasEmpresa;

import com.example.tutorialfirebase.Clases.Empresa;
import com.example.tutorialfirebase.Modelos.EmpresaDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerEmpresas implements Callable<ArrayList<Empresa>> {
    private ArrayList<Empresa> empresasDevueltas = null;
    private int página;

    public TareaObtenerEmpresas(int página) {
        this.página = página;
    }

    @Override
    public ArrayList<Empresa> call() throws Exception {
        empresasDevueltas = EmpresaDB.obtenerEmpresa(página);
        return empresasDevueltas;
    }
}
