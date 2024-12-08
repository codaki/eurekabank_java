package com.example.eurekabank_restful_java_climov.models;

public class MovimientoModel {
    private String codigoCuenta;
    private String codigoEmpleado;
    private String codigoTipoMovimiento;
    private String tipoDescripcion;
    private String cuentaReferencia;
    private String fechaMovimiento;
    private double importeMovimiento;
    private int numeroMovimiento;

    public String getTipoDescripcion() {
        return tipoDescripcion;
    }

    public void setTipoDescripcion(String tipoDescripcion) {
        this.tipoDescripcion = tipoDescripcion;
    }

    // Getters
    public String getCodigoCuenta() { return codigoCuenta; }
    public String getCodigoEmpleado() { return codigoEmpleado; }
    public String getCodigoTipoMovimiento() { return codigoTipoMovimiento; }
    public String getCuentaReferencia() { return cuentaReferencia; }
    public String getFechaMovimiento() { return fechaMovimiento; }
    public double getImporteMovimiento() { return importeMovimiento; }
    public int getNumeroMovimiento() { return numeroMovimiento; }

    // Setters
    public void setCodigoCuenta(String codigoCuenta) { this.codigoCuenta = codigoCuenta; }
    public void setCodigoEmpleado(String codigoEmpleado) { this.codigoEmpleado = codigoEmpleado; }
    public void setCodigoTipoMovimiento(String codigoTipoMovimiento) { this.codigoTipoMovimiento = codigoTipoMovimiento; }
    public void setCuentaReferencia(String cuentaReferencia) { this.cuentaReferencia = cuentaReferencia; }
    public void setFechaMovimiento(String fechaMovimiento) { this.fechaMovimiento = fechaMovimiento; }
    public void setImporteMovimiento(double importeMovimiento) { this.importeMovimiento = importeMovimiento; }
    public void setNumeroMovimiento(int numeroMovimiento) { this.numeroMovimiento = numeroMovimiento; }
}
