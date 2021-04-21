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

    //COD_EMPRESA NO ES ESTA TENIENDO EN CUENTA EN NINGUNA CONSULTA

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
                int habilitado = resultado.getInt("habilitado");
                int archivado = resultado.getInt("archivado");
                String cod_producto = resultado.getString("cod_product");
                ProductosPublicados p = new ProductosPublicados(idproductoempresa, cantidad, precioventa, habilitado, archivado, cod_producto);
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
                String cod_producto = resultado.getString("cod_product");
                ProductosPublicados p = new ProductosPublicados(idproductoempresa, cantidad, precioventa, habilitado, archivado, cod_producto);
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

    public static boolean actualizarProducto(ProductosPublicados p){ //Se supone que el idproductoempresa no se puede modificar
        Connection conexión = BaseDB.conectarConBaseDeDatos();
        if (conexión == null) {
            Log.i("SQL", "Error al establecer la conexión con la base de datos");
            return false;
        }
        try {
            //Recojo el idproductoempresa
            int idproductoempresa = 0;
            String ordenSQL1 = "SELECT idproductoempresa FROM productospublicados WHERE precioventa = ?;";
            PreparedStatement sentenciaPreparada1 = conexión.prepareStatement(ordenSQL1);
            sentenciaPreparada1.setDouble(1, p.getPrecioventa());
            ResultSet resultado = sentenciaPreparada1.executeQuery();
            while(resultado.next()) {
                idproductoempresa = resultado.getInt("idproductoempresa");
            }
            resultado.close();
            sentenciaPreparada1.close();

            String ordenSQL2 = "UPDATE productospublicados SET cantidad = ?, precioventa = ?, habilitado = ?, archivado = ?, cod_producto = ? WHERE idproductoempresa = ?";
            PreparedStatement sentenciaPreparada2 = conexión.prepareStatement(ordenSQL2);
            sentenciaPreparada2.setInt(1, p.getCantidad());
            sentenciaPreparada2.setDouble(2, p.getPrecioventa());
            sentenciaPreparada2.setInt(3, p.getHabilitado());
            sentenciaPreparada2.setInt(4, p.getArchivado());
            sentenciaPreparada2.setString(5, p.getCod_producto());
            sentenciaPreparada2.setInt(6, idproductoempresa);
            int filasAfectadas1 = sentenciaPreparada2.executeUpdate();
            sentenciaPreparada2.close();

            /*
            COMO HAGO ESTO¿?
            String ordenSQL3 = "UPDATE productos SET cod_producto = ? WHERE idproductoempresa = ?";
            PreparedStatement sentenciaPreparada3 = conexión.prepareStatement(ordenSQL3);
            sentenciaPreparada3.setString(1, p.getCod_producto());
            sentenciaPreparada3.setInt(2, idproductoempresa);
            int filasAfectadas2 = sentenciaPreparada3.executeUpdate();
            sentenciaPreparada3.close();
            */
            conexión.close();

            if ((filasAfectadas1 > 0 && filasAfectadas2 > 0) || (filasAfectadas1 == 0 && filasAfectadas2 > 0)){ //La consulta se ejecutará si se han actualizado 2 filas (1ª condicion del if) o si solo se han actualizado filas en la tabla moda (2ª condicion del if)
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            Log.i("SQL", "Error al actualizar el producto publicado en la base de datos");
            return false;
        }
    }
}
