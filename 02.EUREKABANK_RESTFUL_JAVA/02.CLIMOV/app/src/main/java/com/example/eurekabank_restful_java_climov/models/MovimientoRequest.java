package com.example.eurekabank_restful_java_climov.models;

public class MovimientoRequest {
    private String numcuenta;

    public MovimientoRequest(String numcuenta) {
        this.numcuenta = numcuenta;
    }

    public String getNumcuenta() {
        return numcuenta;
    }

    public void setNumcuenta(String numcuenta) {
        this.numcuenta = numcuenta;
    }
}