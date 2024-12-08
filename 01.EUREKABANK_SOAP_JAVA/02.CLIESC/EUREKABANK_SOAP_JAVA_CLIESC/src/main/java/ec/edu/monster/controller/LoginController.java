package ec.edu.monster.controller;

import ec.edu.monster.view.CuentaView;
import ec.edu.monster.view.LoginView;
import ec.edu.monster.view.MovimientoView;
import ec.edu.monster.ws.LoginService;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import java.security.MessageDigest;
import javax.swing.JOptionPane;

public class LoginController {
    
    private LoginView loginView;
    private MovimientoView cuentaView;

    private LoginService loginService = new LoginService();
    private String usuarioActual = null;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }
    
    public String getUsuarioActual() {
        return usuarioActual;
    }

    public boolean iniciarSesion(String username, String password) {
        try {
            String hashedPassword = hashPassword(password);
            if (loginService.auth(username, hashedPassword)) {
                usuarioActual = username;
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error en la autenticación: " + e.getMessage());
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

    private String hashPassword(String input) throws NoSuchAlgorithmException {
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
