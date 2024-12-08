package com.example.eurekabank_restful_java_climov.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.eurekabank_restful_java_climov.R;
import com.example.eurekabank_restful_java_climov.controller.CuentaController;
import com.example.eurekabank_restful_java_climov.models.MovimientoModel;

import java.util.List;

public class CuentaActivity extends AppCompatActivity {
    private EditText etCuenta;
    private EditText etMonto;
    private Button btnProcesar;
    private Button btnRegresar;
    private CuentaController cuentaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cuenta);
        etCuenta = findViewById(R.id.etCuenta);
        etMonto = findViewById(R.id.etMonto);
        btnProcesar = findViewById(R.id.btnProcesar);
        btnRegresar = findViewById(R.id.btnRegresar);

        cuentaController = new CuentaController();

        btnProcesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesarCuenta();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CuentaActivity.this, MovimentosActivity.class));
                finish();
            }
        });
    }

    private void procesarCuenta() {
        String numeroCuenta = etCuenta.getText().toString().trim();
        String montoStr = etMonto.getText().toString().trim();

        if (numeroCuenta.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un número de cuenta", Toast.LENGTH_SHORT).show();
            return;
        }

        if (montoStr.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un monto", Toast.LENGTH_SHORT).show();
            return;
        }

        double monto = Double.parseDouble(montoStr);
        cuentaController.procesarCuenta(numeroCuenta, monto, new CuentaController.CuentaCallback() {
            @Override
            public void onCuentaSuccess(Boolean response) {
                Toast.makeText(CuentaActivity.this, "Depósito Procesado Correctamente", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CuentaActivity.this, MovimentosActivity.class));
                finish();
            }

            @Override
            public void onCuentaError(String message) {
                Toast.makeText(CuentaActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}