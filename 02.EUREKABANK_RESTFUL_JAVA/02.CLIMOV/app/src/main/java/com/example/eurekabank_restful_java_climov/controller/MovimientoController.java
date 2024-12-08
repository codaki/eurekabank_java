package com.example.eurekabank_restful_java_climov.controller;

import android.util.Log;

import com.example.eurekabank_restful_java_climov.models.MovimientoModel;
import com.example.eurekabank_restful_java_climov.models.MovimientoRequest;
import com.example.eurekabank_restful_java_climov.service.ApiService;
import com.example.eurekabank_restful_java_climov.service.RetroFitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovimientoController {
    private static final String TAG = "MovimientoController";
    private ApiService apiService;

    public interface MovimientoCallback {
        void onMovimientosSuccess(List<MovimientoModel> movimientos);
        void onMovimientosError(String errorMessage);
    }

    public MovimientoController() {
        // Initialize ApiService directly in the constructor
        this.apiService = RetroFitClient.getInstance().create(ApiService.class);
    }

    public void obtenerMovimientos(String numeroCuenta, final MovimientoCallback callback) {
        Log.d(TAG, "Requesting movements for account: " + numeroCuenta); // Log the request body

        MovimientoRequest movimientoRequest = new MovimientoRequest(numeroCuenta);
        Call<List<MovimientoModel>> call = apiService.movimientos(movimientoRequest);
        call.enqueue(new Callback<List<MovimientoModel>>() {
            @Override
            public void onResponse(Call<List<MovimientoModel>> call, Response<List<MovimientoModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MovimientoModel> movimientos = response.body();
                    Log.d(TAG, "Success! Found " + movimientos.size() + " transactions");
                    callback.onMovimientosSuccess(movimientos);
                } else {
                    String errorMessage = "Error: Unable to fetch movements. Response code: " + response.code() + ", message: " + response.message();
                    Log.e(TAG, errorMessage);
                    callback.onMovimientosError(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<List<MovimientoModel>> call, Throwable t) {
                String errorMessage = "Network error: " + t.getMessage();
                Log.e(TAG, errorMessage);
                callback.onMovimientosError(errorMessage);
            }
        });
    }
}