package com.example.tutorialfirebase.Clases;

public class Moda {
    //ATRIBUTOS
    private String cod_producto;
    private String talla;
    private String color;
    private String material;
    private String sexo;
    private String categoria_moda;

    //CONSTRUCTORES
    public Moda(String cod_producto, String talla, String color, String material, String sexo, String categoria_moda) {
        this.cod_producto = cod_producto;
        this.talla = talla;
        this.color = color;
        this.material = material;
        this.sexo = sexo;
        this.categoria_moda = categoria_moda;
    }

    //GETTERS & SETTERS
    public String getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(String cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCategoria_moda() {
        return categoria_moda;
    }

    public void setCategoria_moda(String categoria_moda) {
        this.categoria_moda = categoria_moda;
    }

    //TO STRING
    @Override
    public String toString() {
        return "Moda{" +
                "cod_producto=" + cod_producto +
                ", talla='" + talla + '\'' +
                ", color='" + color + '\'' +
                ", material='" + material + '\'' +
                ", sexo='" + sexo + '\'' +
                ", categoria_moda='" + categoria_moda + '\'' +
                '}';
    }
}
