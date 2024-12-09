package com.example.eurekabank_restful_java_climov.models;

public class CuentaModel {
    private String cuenta;
    private double monto;
    private String tipo;
    private String cd;

    public CuentaModel() {
    }

    public CuentaModel(String cuenta, double monto, String tipo, String cd) {
        this.cuenta = cuenta;
        this.monto = monto;
        this.tipo = tipo;
        this.cd = cd;
    }

    // Getters
    public String getCuenta() { return cuenta; }
    public double getMonto() { return monto; }
    public String getTipo() { return tipo; }
    public String getCd() { return cd; }

    // Setters
    public void setCuenta(String cuenta) { this.cuenta = cuenta; }
    public void setMonto(double monto) { this.monto = monto; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setCd(String cd) { this.cd = cd; }
}