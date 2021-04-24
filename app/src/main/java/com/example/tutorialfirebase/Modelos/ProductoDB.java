package com.example.tutorialfirebase.Modelos;

import android.util.Log;

import com.example.tutorialfirebase.Clases.Producto;
import com.example.tutorialfirebase.Modelos.ConfiguraciónDB.BaseDB;
import com.example.tutorialfirebase.Modelos.ConfiguraciónDB.ConfiguracionesGeneralesDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductoDB {
    public static ArrayList<Producto> obtenerProducto(int página){
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if(conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return null;
        }
        ArrayList<Producto> productosDevueltos = new ArrayList<Producto>();
        try {
            Statement sentencia = conexión.createStatement();
            int desplazamiento = página * ConfiguracionesGeneralesDB.ELEMENTOS_POR_PAGINA;
            String ordenSQL = "SELECT * FROM productos LIMIT" + desplazamiento + ", " + ConfiguracionesGeneralesDB.ELEMENTOS_POR_PAGINA;
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next()) {
                String cod_producto = resultado.getString("cod_producto");
                String cod_QR = resultado.getString("cod_QR");
                String marca = resultado.getString("marca");
                String modelo = resultado.getString("modelo");
                String descripcion = resultado.getString("descripcion");
                Producto p = new Producto(cod_producto, cod_QR, marca, modelo, descripcion);
                productosDevueltos.add(p);
            }
            resultado.close();
            sentencia.close();

            conexión.close();

            return productosDevueltos;
        } catch (SQLException e) {
            Log.i("SQL", "Error al mostrar los productos de la base de datos");
            return null;
        }
    }

    public static ArrayList<Producto> buscarProducto(String nombreP) {
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if (conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return null;
        }
        ArrayList<Producto> productosEncontrados = new ArrayList<Producto>();
        try {
            ResultSet resultado = BaseDB.buscarFilas(conexión, "productos", "cod_producto", nombreP);
            if (resultado == null) {
                return null;
            }
            while (resultado.next()) {
                String cod_producto = resultado.getString("cod_producto");
                String cod_QR = resultado.getString("cod_QR");
                String marca = resultado.getString("marca");
                String modelo = resultado.getString("modelo");
                String descripcion = resultado.getString("descripcion");
                Producto e = new Producto(cod_producto, cod_QR, marca, modelo, descripcion);
                productosEncontrados.add(e);
            }
            resultado.close();

            conexión.close();

            return productosEncontrados;
        } catch (SQLException e) {
            Log.i("SQL", "Error al buscar los productos en la base de datos");
            return null;
        }
    }

    public static int obtenerCantidadProductos() {
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if (conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return 0;
        }
        int cantidadProductos = 0;
        try {
            Statement sentencia = conexión.createStatement();
            String ordenSQL = "SELECT count(*) as cantidad FROM productos";
            ResultSet resultado  = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
                cantidadProductos = resultado.getInt("cantidad");
            }
            resultado.close();
            sentencia.close();

            conexión.close();

            return cantidadProductos;
        } catch (SQLException e) {
            Log.i("SQL", "Error al devolver el número de productos de la base de datos");
            return 0;
        }
    }

    public static boolean insertarProducto(Producto p){
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if (conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return false;
        }
        try {
            String ordenSQL = "INSERT INTO productos (cod_producto, cod_QR, marca, modelo, descripcion) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement sentenciaPreparada = conexión.prepareStatement(ordenSQL);
            sentenciaPreparada.setString(1, p.getCod_producto());
            sentenciaPreparada.setString(2, p.getCod_QR());
            sentenciaPreparada.setString(3, p.getMarca());
            sentenciaPreparada.setString(4, p.getModelo());
            sentenciaPreparada.setString(5, p.getDescripción());
            int filasAfectadas = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();

            conexión.close();

            if (filasAfectadas > 0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e){
            Log.i("SQL", "Error al insertar el producto en la base de datos");
            return false;
        }
    }

    public static boolean borrarProducto(Producto p){
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if (conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return false;
        }
        try {
            String ordenSQL = "DELETE FROM productos WHERE cod_producto = ?;";
            PreparedStatement sentenciaPreparada = conexión.prepareStatement(ordenSQL);
            sentenciaPreparada.setString(1, p.getCod_producto());
            int filasAfectadas = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();

            conexión.close();

            if(filasAfectadas > 0) {
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            Log.i("SQL", "Error al borrar el producto en la base de datos");
            return false;
        }

    }

    public static boolean actualizarProducto(Producto p){
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if (conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return false;
        }
        try {
            //Recojo el cod_producto
            String cod_producto = "";
            String ordenSQL1 = "SELECT cod_producto FROM productos WHERE modelo = ?;";
            PreparedStatement sentenciaPreparada1 = conexión.prepareStatement(ordenSQL1);
            sentenciaPreparada1.setString(1, p.getModelo());
            ResultSet resultado = sentenciaPreparada1.executeQuery();
            while(resultado.next()) {
                cod_producto = resultado.getString("cod_producto");
            }
            resultado.close();
            sentenciaPreparada1.close();

            String ordenSQL2 = "UPDATE productos SET cod_QR = ?, marca = ?, modelo = ?, descripcion = ? WHERE cod_producto = ?";
            PreparedStatement sentenciaPreparada2 = conexión.prepareStatement(ordenSQL2);
            sentenciaPreparada2.setString(1, p.getCod_QR());
            sentenciaPreparada2.setString(2, p.getMarca());
            sentenciaPreparada2.setString(3, p.getModelo());
            sentenciaPreparada2.setString(4, p.getDescripción());
            sentenciaPreparada2.setString(5, cod_producto);
            int filasAfectadas = sentenciaPreparada2.executeUpdate();
            sentenciaPreparada2.close();

            conexión.close();

            if (filasAfectadas > 0){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            Log.i("SQL", "Error al actualizar el producto en la base de datos");
            return false;
        }
    }
}
