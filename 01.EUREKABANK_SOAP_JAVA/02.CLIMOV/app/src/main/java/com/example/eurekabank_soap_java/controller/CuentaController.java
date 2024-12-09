package com.example.eurekabank_soap_java.controller;

import com.example.eurekabank_soap_java.service.CuentaService;

public class CuentaController {
    private CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    public interface CuentaCallback {
        void onCuentaSuccess(boolean result);
        void onCuentaError(String errorMessage);
    }

    public void procesarOperacion(String numeroCuenta, double monto, String tipoOperacion, String cuentaDestino, final CuentaCallback callback) {
        cuentaService.procesarOperacion(numeroCuenta, monto, tipoOperacion, cuentaDestino, new CuentaService.CuentaCallback() {
            @Override
            public void onSuccess(boolean result) {
                callback.onCuentaSuccess(result);
            }

            @Override
            public void onError(String errorMessage) {
                callback.onCuentaError(errorMessage);
            }
        });
    }
}