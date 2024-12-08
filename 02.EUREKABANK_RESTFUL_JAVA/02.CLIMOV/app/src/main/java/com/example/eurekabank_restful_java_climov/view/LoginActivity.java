package com.example.eurekabank_restful_java_climov.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eurekabank_restful_java_climov.R;
import com.example.eurekabank_restful_java_climov.controller.HashUtils;
import com.example.eurekabank_restful_java_climov.controller.LoginController;

public class LoginActivity extends AppCompatActivity {

    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Initialize LoginController
        loginController = new LoginController();

        EditText etUsername = findViewById(R.id.etUsername);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos.", Toast.LENGTH_SHORT).show();
            } else {
                String hashedPassword = HashUtils.hashPassword(password);
                loginUser(username, hashedPassword);
            }
        });
    }

    private void loginUser(String username, String password) {
        loginController.autenticar(username, password, this);
    }
}