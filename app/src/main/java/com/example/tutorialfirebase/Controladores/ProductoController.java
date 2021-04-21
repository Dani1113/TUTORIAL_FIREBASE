package com.example.tutorialfirebase.Controladores;

import com.example.tutorialfirebase.Clases.Producto;
import com.example.tutorialfirebase.Tareas.TareasProducto.TareaActualizarProducto;
import com.example.tutorialfirebase.Tareas.TareasProducto.TareaBorrarProducto;
import com.example.tutorialfirebase.Tareas.TareasProducto.TareaBuscarProducto;
import com.example.tutorialfirebase.Tareas.TareasProducto.TareaCantidadProducto;
import com.example.tutorialfirebase.Tareas.TareasProducto.TareaInsertarProducto;
import com.example.tutorialfirebase.Tareas.TareasProducto.TareaObtenerProducto;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ProductoController {
    public static ArrayList<Producto> obtenerProducto(int página) {
        ArrayList<Producto> productoDevuelto = null;
        FutureTask tarea = new FutureTask(new TareaObtenerProducto(página));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            productoDevuelto = (ArrayList<Producto>) tarea.get();
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
        return productoDevuelto;
    }

    public static ArrayList<Producto> buscarProducto(String modelo){
        ArrayList<Producto> productoEncontrado = null;
        FutureTask tarea = new FutureTask(new TareaBuscarProducto(modelo));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            productoEncontrado = (ArrayList<Producto>) tarea.get();
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
        return productoEncontrado;
    }

    public static int obtenerCantidadProducto() {
        int cantidadProducto = 0;
        FutureTask tarea = new FutureTask (new TareaCantidadProducto());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            cantidadProducto = (int) tarea.get();
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
        return cantidadProducto;
    }

    public static boolean insertarProducto(Producto p) {
        FutureTask tarea = new FutureTask(new TareaInsertarProducto(p));
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

    public static boolean borrarProducto(Producto p) {
        FutureTask tarea = new FutureTask(new TareaBorrarProducto(p));
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

    public static boolean actualizarProducto(Producto p) {
        FutureTask tarea = new FutureTask(new TareaActualizarProducto(p));
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
