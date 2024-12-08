package com.example.eurekabank_restful_java_climov.controller;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.eurekabank_restful_java_climov.models.LoginModel;
import com.example.eurekabank_restful_java_climov.service.ApiService;
import com.example.eurekabank_restful_java_climov.service.RetroFitClient;
import com.example.eurekabank_restful_java_climov.view.MovimentosActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginController {

    public void autenticar(String username, String password, Context context) {
        ApiService apiService = RetroFitClient.getInstance().create(ApiService.class);
        LoginModel loginRequest = new LoginModel(username, password);

        Call<Boolean> call = apiService.login(loginRequest);
        Log.d("Login", call.request().toString());
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Boolean loginResult = response.body();
                    if (loginResult) {
                        // Login successful
                        Log.d("Login", "Inicio de sesión exitoso");
                        Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, MovimentosActivity.class);
                        context.startActivity(intent);
                    } else {
                        // Login failed
                        Log.d("Login", "Credenciales incorrectas");
                        Toast.makeText(context, "Credenciales incorrectas. Inténtelo de nuevo.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Unsuccessful response
                    Log.d("Login", "Respuesta no exitosa");
                    Toast.makeText(context, "Error al iniciar sesión. Inténtelo más tarde.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.e("Login", "Error: " + t.getMessage());
                Toast.makeText(context, "Error de conexión. Verifique su red.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}