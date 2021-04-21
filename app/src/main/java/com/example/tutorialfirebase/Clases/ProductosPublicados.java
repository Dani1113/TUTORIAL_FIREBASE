package com.example.tutorialfirebase.Clases;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

public class ProductosPublicados {
    //ATRIBUTOS
    private int idproductoempresa;
    private int cantidad;
    private double precioventa;
    private int habilitado;
    private int archivado;
    private String cod_producto; //¿Deberia ser un atributo de tipo Producto?
    //private Empresa cod_empresa; ¿La tabla empresa la vamos a usar al final?

    //CONSTRUCTORES
    public ProductosPublicados(int idproductoempresa, int cantidad, double precioventa, int habilitado, int archivado, String cod_producto) {
        this.idproductoempresa = idproductoempresa;
        this.cantidad = cantidad;
        this.precioventa = precioventa;
        this.habilitado = habilitado;
        this.archivado = archivado;
        this.cod_producto = cod_producto;
    }

    //GETTERS & SETTERS
    public int getIdproductoempresa() {
        return idproductoempresa;
    }

    public void setIdproductoempresa(int idproductoempresa) {
        this.idproductoempresa = idproductoempresa;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(double precioventa) {
        this.precioventa = precioventa;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public int getArchivado() {
        return archivado;
    }

    public void setArchivado(int archivado) {
        this.archivado = archivado;
    }

    public String getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(String cod_producto) {
        this.cod_producto = cod_producto;
    }

    //HASH CODE & EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductosPublicados that = (ProductosPublicados) o;
        return idproductoempresa == that.idproductoempresa;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(idproductoempresa);
    }

    //TO STRING
    @Override
    public String toString() {
        return "ProductosPublicados{" +
                "idproductoempresa=" + idproductoempresa +
                ", cantidad=" + cantidad +
                ", precioventa=" + precioventa +
                ", habilitado=" + habilitado +
                ", archivado=" + archivado +
                ", cod_producto=" + cod_producto +
                '}';
    }
}
