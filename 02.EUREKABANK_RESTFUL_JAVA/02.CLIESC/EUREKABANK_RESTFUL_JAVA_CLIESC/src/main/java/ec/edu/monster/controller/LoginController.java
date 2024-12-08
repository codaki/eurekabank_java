package ec.edu.monster.controller;

import ec.edu.monster.model.LoginRequest;
import ec.edu.monster.view.LoginView;
import ec.edu.monster.view.MovimientoView;
import ec.edu.monster.ws.WSLogin;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    private String usuarioActual = null;
    private WSLogin wsLogin = new WSLogin();
    
    private LoginView loginView;
    private MovimientoView cuentaView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }
    public String getUsuarioActual() {
        return usuarioActual;
    }

    public boolean iniciarSesion(String username, String password) {
    LoginRequest request = null;
        try {
            request = new LoginRequest(username, hashPassword(password));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    try {
        String response = wsLogin.authenticate(request, String.class);
        System.out.println(response);
        if ("true".equalsIgnoreCase(response)) {
            usuarioActual = username;
            return true;
        }
    } catch (Exception e) {
        System.out.println("Error en la autenticación: " + e.getMessage());
    }
    return false;
}
    
    public void autenticar(String username, String password) {
        if (iniciarSesion(username, password)) {
            loginView.dispose();
            MovimientoView cuenta = new MovimientoView();
            cuenta.setVisible(true);
        } else {
            loginView.getMessageLabel().setText("Usuario o contraseña inválidos.");
        }
    }


    public void cerrarSesion() {
        usuarioActual = null;
    }
    
    private static String hashPassword(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
