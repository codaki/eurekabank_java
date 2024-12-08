package com.example.eurekabank_restful_java_climov.service;

import com.example.eurekabank_restful_java_climov.models.CuentaModel;
import com.example.eurekabank_restful_java_climov.models.LoginModel;
import com.example.eurekabank_restful_java_climov.models.MovimientoModel;
import com.example.eurekabank_restful_java_climov.models.MovimientoRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login")
    Call<Boolean> login(@Body LoginModel loginRequest);

    @POST("movimiento")
    Call<List<MovimientoModel>> movimientos(@Body MovimientoRequest movimientoRequest);

    @POST("cuenta")
    Call<Boolean> cuenta(@Body CuentaModel cuentaRequest);
}
