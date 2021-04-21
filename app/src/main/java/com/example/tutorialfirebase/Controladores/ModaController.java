package com.example.tutorialfirebase.Controladores;

import com.example.tutorialfirebase.Clases.Moda;
import com.example.tutorialfirebase.Tareas.TareasModa.TareaActualizarModa;
import com.example.tutorialfirebase.Tareas.TareasModa.TareaBorrarModa;
import com.example.tutorialfirebase.Tareas.TareasModa.TareaBuscarModa;
import com.example.tutorialfirebase.Tareas.TareasModa.TareaCantidadModa;
import com.example.tutorialfirebase.Tareas.TareasModa.TareaInsertarModa;
import com.example.tutorialfirebase.Tareas.TareasModa.TareaObtenerModa;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ModaController {
    public static ArrayList<Moda> obtenerModa(int página) {
        ArrayList<Moda> modaDevuelta = null;
        FutureTask tarea = new FutureTask(new TareaObtenerModa(página));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            modaDevuelta = (ArrayList<Moda>) tarea.get();
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
        return modaDevuelta;
    }

    public static ArrayList<Moda> buscarModa(String talla){
        ArrayList<Moda> modaEncontrada = null;
        FutureTask tarea = new FutureTask(new TareaBuscarModa(talla));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            modaEncontrada = (ArrayList<Moda>) tarea.get();
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
        return modaEncontrada;
    }

    public static int obtenerCantidadModa() {
        int cantidadModa = 0;
        FutureTask tarea = new FutureTask (new TareaCantidadModa());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            cantidadModa = (int) tarea.get();
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
        return cantidadModa;
    }

    public static boolean insertarModa(Moda m) {
        FutureTask tarea = new FutureTask(new TareaInsertarModa(m));
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

    public static boolean borrarModa(Moda m) {
        FutureTask tarea = new FutureTask(new TareaBorrarModa(m));
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

    public static boolean actualizarModa(Moda m) {
        FutureTask tarea = new FutureTask(new TareaActualizarModa(m));
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
