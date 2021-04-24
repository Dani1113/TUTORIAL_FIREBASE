package com.example.tutorialfirebase.Modelos;

import android.util.Log;

import com.example.tutorialfirebase.Clases.Producto;
import com.example.tutorialfirebase.Clases.ProductosPublicados;
import com.example.tutorialfirebase.Modelos.ConfiguraciónDB.BaseDB;
import com.example.tutorialfirebase.Modelos.ConfiguraciónDB.ConfiguracionesGeneralesDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductosPublicadosDB {
    public static ArrayList<ProductosPublicados> obtenerProductosPublicados(int página){
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if(conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return null;
        }
        ArrayList<ProductosPublicados> productosPublicadosDevueltos = new ArrayList<ProductosPublicados>();
        try {
            Statement sentencia = conexión.createStatement();
            int desplazamiento = página * ConfiguracionesGeneralesDB.ELEMENTOS_POR_PAGINA;
            String ordenSQL = "SELECT * FROM productospublicados LIMIT" + desplazamiento + ", " + ConfiguracionesGeneralesDB.ELEMENTOS_POR_PAGINA;
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next()) {
                int idproductoempresa = resultado.getInt("idproductoempresa");
                int cantidad = resultado.getInt("cantidad");
                double precioventa = resultado.getDouble("precioventa");
                boolean habilitado = resultado.getBoolean("habilitado");
                boolean archivado = resultado.getBoolean("archivado");
                String cod_producto = resultado.getString("cod_producto");
                String cod_empresa = resultado.getString("cod_empresa");
                ProductosPublicados p = new ProductosPublicados(idproductoempresa, cantidad, precioventa, habilitado, archivado, cod_producto, cod_empresa);
                productosPublicadosDevueltos.add(p);
            }
            resultado.close();
            sentencia.close();

            conexión.close();

            return productosPublicadosDevueltos;
        } catch (SQLException e) {
            Log.i("SQL", "Error al mostrar los productos publicados de la base de datos");
            return null;
        }
    }

    public static ArrayList<ProductosPublicados> buscarProductoPublicados(String nombreP) {
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if (conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return null;
        }
        ArrayList<ProductosPublicados> productosPublicadosEncontrados = new ArrayList<ProductosPublicados>();
        try {
            ResultSet resultado = BaseDB.buscarFilas(conexión, "productospublicados", "idproductoempresa", nombreP);
            if (resultado == null) {
                return null;
            }
            while (resultado.next()) {
                int idproductoempresa = resultado.getInt("idproductoempresa");
                int cantidad = resultado.getInt("cantidad");
                double precioventa = resultado.getDouble("precioventa");
                int habilitado = resultado.getInt("habilitado");
                int archivado = resultado.getInt("archivado");
                String cod_producto = resultado.getString("cod_producto");
                String cod_empresa = resultado.getString("cod_empresa");
                ProductosPublicados p = new ProductosPublicados(idproductoempresa, cantidad, precioventa, habilitado, archivado, cod_producto, cod_empresa);
                productosPublicadosEncontrados.add(p);
            }
            resultado.close();

            conexión.close();

            return productosPublicadosEncontrados;
        } catch (SQLException e) {
            Log.i("SQL", "Error al buscar los productos publicados en la base de datos");
            return null;
        }
    }

    public static int obtenerCantidadProductosPublicados() {
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if (conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return 0;
        }
        int cantidadProductosPublicados = 0;
        try {
            Statement sentencia = conexión.createStatement();
            String ordenSQL = "SELECT count(*) as cantidad FROM productospublicados";
            ResultSet resultado  = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
                cantidadProductosPublicados = resultado.getInt("cantidad");
            }
            resultado.close();
            sentencia.close();

            conexión.close();

            return cantidadProductosPublicados;
        } catch (SQLException e) {
            Log.i("SQL", "Error al devolver el número de productos publicados de la base de datos");
            return 0;
        }
    }

    public static boolean insertarProductoPublicado(ProductosPublicados p){ //¿Se puede insertar un producto publicadop con un cod_producto que no esta previamente creado? De ser así habría que hacer un insert en productos y por tanto el cod_producto de productospublicados, debería de ser un atributo de tipo Producto, para poder hacer la insercion
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if (conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return false;
        }
        try {
            String ordenSQL = "INSERT INTO productospublicados (idproductoempresa, cantidad, precioventa, habilitado, archivado, cod_producto) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement sentenciaPreparada = conexión.prepareStatement(ordenSQL);
            sentenciaPreparada.setInt(1, p.getIdproductoempresa());
            sentenciaPreparada.setInt(2, p.getCantidad());
            sentenciaPreparada.setDouble(3, p.getPrecioventa());
            sentenciaPreparada.setInt(4, p.getHabilitado());
            sentenciaPreparada.setInt(5, p.getArchivado());
            sentenciaPreparada.setString(6, p.getCod_producto());
            int filasAfectadas = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();

            conexión.close();

            if (filasAfectadas > 0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e){
            Log.i("SQL", "Error al insertar el producto publicado en la base de datos");
            return false;
        }
    }

    public static boolean borrarProductoPublicado(ProductosPublicados p){
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if (conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return false;
        }
        try {
            String ordenSQL = "DELETE FROM productospublicados WHERE idproductoempresa = ?;";
            PreparedStatement sentenciaPreparada = conexión.prepareStatement(ordenSQL);
            sentenciaPreparada.setInt(1, p.getIdproductoempresa());
            int filasAfectadas = sentenciaPreparada.executeUpdate();
            sentenciaPreparada.close();

            conexión.close();

            if(filasAfectadas > 0) {
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            Log.i("SQL", "Error al borrar el producto publicado en la base de datos");
            return false;
        }

    }
}
