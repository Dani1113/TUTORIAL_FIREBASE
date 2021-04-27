package com.example.tutorialfirebase.Clases;

import java.io.Serializable;

public class InfoEmpresa implements Serializable {
    private String logoURL;
    private String nombre;
    private String sector;
    private String resumen;
    private String direccion;

    public InfoEmpresa(String logoURL, String nombre, String sector, String resumen, String direccion) {
        this.logoURL = logoURL;
        this.nombre = nombre;
        this.sector = sector;
        this.resumen = resumen;
        this.direccion = direccion;
    }

    public InfoEmpresa() {
        this.logoURL = "logoURL";
        this.nombre = "nombre";
        this.sector = "sector";
        this.resumen = "resumen";
        this.direccion = "direccion";
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "InfoEmpresa{" +
                "logoURL='" + logoURL + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sector='" + sector + '\'' +
                ", resumen='" + resumen + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
