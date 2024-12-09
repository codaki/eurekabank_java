package com.example.eurekabank_restful_java_climov.controller;

import android.text.BoringLayout;
import android.util.Log;

import com.example.eurekabank_restful_java_climov.models.CuentaModel;
import com.example.eurekabank_restful_java_climov.models.MovimientoModel;
import com.example.eurekabank_restful_java_climov.service.ApiService;
import com.example.eurekabank_restful_java_climov.service.RetroFitClient;

import java.util.List;

import retrofit2.Call;

public class CuentaController {
    private static final String TAG = "MovimientoController";
    private ApiService apiService;

    public interface CuentaCallback {
        void onCuentaSuccess(Boolean response);
        void onCuentaError(String errorMessage);
    }

    public CuentaController() {
        // Initialize ApiService directly in the constructor
        this.apiService = RetroFitClient.getInstance().create(ApiService.class);
    }

    public void procesarOperacion(String numeroCuenta, double monto, String tipo, String cd, final CuentaCallback callback) {
        // Log the request body
        Log.d(TAG, "Requesting movements for account: " + numeroCuenta);
        CuentaModel cuentaModel = new CuentaModel(numeroCuenta, monto, tipo, cd);

        // Call the API
        Call<Boolean> response = apiService.cuenta(cuentaModel);
        response.enqueue(new retrofit2.Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, retrofit2.Response<Boolean> response) {
                if (response.isSuccessful()) {
                    callback.onCuentaSuccess(response.body());
                } else {
                    callback.onCuentaError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onCuentaError("Error: " + t.getMessage());
            }
        });
    }
}
