package com.example.eurekabank_restful_java_climov.models;

public class CuentaModel {
    private String cuenta;
    private double monto;

    public CuentaModel() {
    }

    public CuentaModel(String cuenta, double monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    // Getters
    public String getCuenta() { return cuenta; }
    public double getMonto() { return monto; }

    // Setters
    public void setCuenta(String cuenta) { this.cuenta = cuenta; }
    public void setMonto(double monto) { this.monto = monto; }
}