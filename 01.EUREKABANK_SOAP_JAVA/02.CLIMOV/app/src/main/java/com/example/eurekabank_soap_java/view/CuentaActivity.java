package com.example.eurekabank_soap_java.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eurekabank_soap_java.R;
import com.example.eurekabank_soap_java.controller.CuentaController;
import com.example.eurekabank_soap_java.service.CuentaService;

public class CuentaActivity extends AppCompatActivity {
    private EditText etCuenta, etMonto, etCuentaDestino;
    private Button btnProcesar;
    private Spinner spinnerOperacion;
    private Button btnRegresar;
    private CuentaController cuentaController;
    private String tipoOperacion = "DEP"; // Default value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        spinnerOperacion = findViewById(R.id.spinnerOperacion);
        etCuenta = findViewById(R.id.etCuenta);
        etMonto = findViewById(R.id.etMonto);
        etCuentaDestino = findViewById(R.id.etCuentaDestino);
        btnProcesar = findViewById(R.id.btnProcesar);
        btnRegresar = findViewById(R.id.btnRegresar);

        cuentaController = new CuentaController(new CuentaService());

        spinnerOperacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateUIBasedOnOperation(position);
                switch (position) {
                    case 0:
                        tipoOperacion = "DEP";
                        break;
                    case 1:
                        tipoOperacion = "RET";
                        break;
                    case 2:
                        tipoOperacion = "TRA";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        btnProcesar.setOnClickListener(v -> procesarCuenta());

        btnRegresar.setOnClickListener(v -> {
            startActivity(new Intent(CuentaActivity.this, MovimientosActivity.class));
            finish();
        });
    }

    private void updateUIBasedOnOperation(int position) {
        switch (position) {
            case 0:
                etMonto.setHint("Monto");
                etCuentaDestino.setVisibility(View.GONE);
                break;
            case 1:
                etMonto.setHint("Monto a Retirar");
                etCuentaDestino.setVisibility(View.GONE);
                break;
            case 2:
                etMonto.setHint("Monto a Transferir");
                etCuentaDestino.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void procesarCuenta() {
        String numeroCuenta = etCuenta.getText().toString().trim();
        String montoStr = etMonto.getText().toString().trim();
        String cuentaDestino = "";

        if (tipoOperacion.equals("TRA")) {
            cuentaDestino = etCuentaDestino.getText().toString().trim();
        }

        if (numeroCuenta.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un número de cuenta", Toast.LENGTH_SHORT).show();
            return;
        }

        if (montoStr.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un monto", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double monto = Double.parseDouble(montoStr);

            cuentaController.procesarOperacion(numeroCuenta, monto, tipoOperacion, cuentaDestino, new CuentaController.CuentaCallback() {
                @Override
                public void onCuentaSuccess(boolean result) {
                    runOnUiThread(() -> {
                        String mensaje = result ? "Operación exitosa" : "Operación fallida";
                        Toast.makeText(CuentaActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                        if (result) {
                            startActivity(new Intent(CuentaActivity.this, MovimientosActivity.class));
                            finish();
                        }
                    });
                }

                @Override
                public void onCuentaError(String errorMessage) {
                    runOnUiThread(() -> {
                        Toast.makeText(CuentaActivity.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
                    });
                }
            });

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Monto inválido", Toast.LENGTH_SHORT).show();
        }
    }
}