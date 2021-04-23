package com.example.tutorialfirebase.Tareas.TareasEmpresa;

import com.example.tutorialfirebase.Modelos.EmpresaDB;

public class TareaCantidadEmpresas implements java.util.concurrent.Callable {
    private int cantidadEmpresas = 0;

    @Override
    public Object call() throws Exception {
        cantidadEmpresas = EmpresaDB.obtenerCantidadEmpresas();
        return cantidadEmpresas;
    }
}
