package com.example.eurekabank_restful_java_climov.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eurekabank_restful_java_climov.R;
import com.example.eurekabank_restful_java_climov.controller.CuentaController;

public class CuentaActivity extends AppCompatActivity {
    private EditText etCuenta, etMonto, etCuentaDestino;
    private Spinner spinnerOperacion;
    private Button btnProcesar, btnRegresar;
    private CuentaController cuentaController;
    private String tipoOperacion = "DEP"; // Default operation type

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        etCuenta = findViewById(R.id.etCuenta);
        etMonto = findViewById(R.id.etMonto);
        etCuentaDestino = findViewById(R.id.etCuentaDestino);
        spinnerOperacion = findViewById(R.id.spinnerOperacion);
        btnProcesar = findViewById(R.id.btnProcesar);
        btnRegresar = findViewById(R.id.btnRegresar);

        cuentaController = new CuentaController();

        // Handle spinner selection to update the UI based on the operation type
        spinnerOperacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateUIBasedOnOperation(position);
                switch (position) {
                    case 0: tipoOperacion = "DEP"; break; // Deposit
                    case 1: tipoOperacion = "RET"; break; // Withdrawal
                    case 2: tipoOperacion = "TRA"; break; // Transfer
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Default case
            }
        });

        // Process the account operation
        btnProcesar.setOnClickListener(v -> procesarCuenta());

        // Return to the movements activity
        btnRegresar.setOnClickListener(v -> {
            startActivity(new Intent(CuentaActivity.this, MovimentosActivity.class));
            finish();
        });
    }

    // Update UI based on the selected operation
    private void updateUIBasedOnOperation(int position) {
        switch (position) {
            case 0: // Deposit
                etMonto.setHint("Monto");
                etCuentaDestino.setVisibility(View.GONE);
                break;
            case 1: // Withdrawal
                etMonto.setHint("Monto a Retirar");
                etCuentaDestino.setVisibility(View.GONE);
                break;
            case 2: // Transfer
                etMonto.setHint("Monto a Transferir");
                etCuentaDestino.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void procesarCuenta() {
        String numeroCuenta = etCuenta.getText().toString().trim();
        String montoStr = etMonto.getText().toString().trim();
        String cuentaDestino = etCuentaDestino.getText().toString().trim();

        if (numeroCuenta.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un número de cuenta", Toast.LENGTH_SHORT).show();
            return;
        }

        if (montoStr.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese un monto", Toast.LENGTH_SHORT).show();
            return;
        }

        if (tipoOperacion.equals("TRA") && cuentaDestino.isEmpty()) {
            Toast.makeText(this, "Por favor ingrese la cuenta destino", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double monto = Double.parseDouble(montoStr);

            cuentaController.procesarOperacion(numeroCuenta, monto, tipoOperacion, cuentaDestino, new CuentaController.CuentaCallback() {
                @Override
                public void onCuentaSuccess(Boolean result) {
                    String mensaje = result ? "Operación exitosa" : "Operación fallida";
                    Toast.makeText(CuentaActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                    if (result) {
                        startActivity(new Intent(CuentaActivity.this, MovimentosActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCuentaError(String errorMessage) {
                    Toast.makeText(CuentaActivity.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
                }
            });

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Monto inválido", Toast.LENGTH_SHORT).show();
        }
    }
}
