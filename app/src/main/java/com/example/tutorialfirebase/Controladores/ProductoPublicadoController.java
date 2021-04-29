package com.example.tutorialfirebase.Controladores;

import com.example.tutorialfirebase.Clases.ProductosPublicados;
import com.example.tutorialfirebase.Tareas.TareasProductoPublicado.TareaBuscarProductoPublicado;
import com.example.tutorialfirebase.Tareas.TareasProductoPublicado.TareaCantidadProductoPublicado;
import com.example.tutorialfirebase.Tareas.TareasProductoPublicado.TareaObtenerProductoPublicado;
import com.example.tutorialfirebase.Tareas.TareasProductoPublicado.TareaObtenerProductoPublicadoPorEmpresa;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ProductoPublicadoController {
    public static ArrayList<ProductosPublicados> obtenerProductosPublicados(int página) {
        ArrayList<ProductosPublicados> productosPublicadosDevuelto = null;
        FutureTask tarea = new FutureTask(new TareaObtenerProductoPublicado(página));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            productosPublicadosDevuelto = (ArrayList<ProductosPublicados>) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productosPublicadosDevuelto;
    }

    //REPASAR MÉTODO
    public static ArrayList<ProductosPublicados> buscarProductoPublicado(String cod_producto){
        ArrayList<ProductosPublicados> productoPublicadoEncontrado = null;
        FutureTask tarea = new FutureTask(new TareaBuscarProductoPublicado(cod_producto));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            productoPublicadoEncontrado = (ArrayList<ProductosPublicados>) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productoPublicadoEncontrado;
    }

    public static int obtenerCantidadProductoPublicado() {
        int cantidadProductoPublicado = 0;
        FutureTask tarea = new FutureTask (new TareaCantidadProductoPublicado());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            cantidadProductoPublicado = (int) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cantidadProductoPublicado;
    }

    public static ArrayList<ProductosPublicados> obtenerProductosPublicadosPorEmpresa(int página, String cod_empr) {
        ArrayList<ProductosPublicados> productosPublicadosDevuelto = null;
        FutureTask tarea = new FutureTask(new TareaObtenerProductoPublicadoPorEmpresa(página, cod_empr));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            productosPublicadosDevuelto = (ArrayList<ProductosPublicados>) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productosPublicadosDevuelto;
    }

}
