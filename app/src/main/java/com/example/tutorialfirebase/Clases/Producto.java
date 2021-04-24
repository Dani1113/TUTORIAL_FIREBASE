package com.example.tutorialfirebase.Clases;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.Blob;
import java.util.Objects;

public class Producto {
    private String cod_producto;
    private String cod_QR;
    private String marca;
    private String modelo;
    private String descripción;
    private int id_foto;

    public Producto(String cod_producto, String cod_QR, String marca, String modelo, String descripción, int id_foto) {
        this.cod_producto = cod_producto;
        this.cod_QR = cod_QR;
        this.marca = marca;
        this.modelo = modelo;
        this.descripción = descripción;
        this.id_foto = id_foto;
    }

    public String getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(String cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getCod_QR() {
        return cod_QR;
    }

    public void setCod_QR(String cod_QR) {
        this.cod_QR = cod_QR;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public int getId_foto() {
        return id_foto;
    }

    public void setId_foto(int id_foto) {
        this.id_foto = id_foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto productos = (Producto) o;
        return cod_producto.equals(productos.cod_producto);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(cod_producto);
    }

    @Override
    public String toString() {
        return "Producto{" +
                "cod_producto='" + cod_producto + '\'' +
                ", cod_QR='" + cod_QR + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", descripción='" + descripción + '\'' +
                ", id_foto=" + id_foto +
                '}';
    }
}