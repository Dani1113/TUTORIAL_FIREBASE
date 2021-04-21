package com.example.tutorialfirebase.Controladores;

import com.example.tutorialfirebase.Clases.ProductosPublicados;
import com.example.tutorialfirebase.Tareas.TareasProductoPublicado.TareaActualizarProductoPublicado;
import com.example.tutorialfirebase.Tareas.TareasProductoPublicado.TareaBorrarProductoPublicado;
import com.example.tutorialfirebase.Tareas.TareasProductoPublicado.TareaBuscarProductoPublicado;
import com.example.tutorialfirebase.Tareas.TareasProductoPublicado.TareaCantidadProductoPublicado;
import com.example.tutorialfirebase.Tareas.TareasProductoPublicado.TareaInsertarProductoPublicado;
import com.example.tutorialfirebase.Tareas.TareasProductoPublicado.TareaObtenerProductoPublicado;

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

    public static boolean insertarProductoPublicado(ProductosPublicados pp) {
        FutureTask tarea = new FutureTask(new TareaInsertarProductoPublicado(pp));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean insertadoOk = false;
        try {
            insertadoOk = (boolean) tarea.get();
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
        finally {
            return insertadoOk;
        }
    }

    public static boolean borrarProductoPublicado(ProductosPublicados pp) {
        FutureTask tarea = new FutureTask(new TareaBorrarProductoPublicado(pp));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean borradoOk = false;
        try {
            borradoOk = (boolean) tarea.get();
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
        finally {
            return borradoOk;
        }
    }

    public static boolean actualizarProductoPublicado(ProductosPublicados pp) {
        FutureTask tarea = new FutureTask(new TareaActualizarProductoPublicado(pp));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean actualizadoOk = false;
        try {
            actualizadoOk = (boolean) tarea.get();
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
        finally {
            return actualizadoOk;
        }
    }
}
