package com.example.tutorialfirebase.Clases;

public class Empresa {
    private String cod_empresa;
    private String clave_empr;
    private String datos_empr;

    public Empresa(String cod_empresa, String clave_empr, String datos_empr) {
        this.cod_empresa = cod_empresa;
        this.clave_empr = clave_empr;
        this.datos_empr = datos_empr;
    }

    public String getCod_empresa() {
        return cod_empresa;
    }

    public void setCod_empresa(String cod_empresa) {
        this.cod_empresa = cod_empresa;
    }

    public String getClave_empr() {
        return clave_empr;
    }

    public void setClave_empr(String clave_empr) {
        this.clave_empr = clave_empr;
    }

    public String getDatos_empr() {
        return datos_empr;
    }

    public void setDatos_empr(String datos_empr) {
        this.datos_empr = datos_empr;
    }
}
