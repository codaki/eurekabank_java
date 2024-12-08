package ec.edu.monster.controllers;

import ec.edu.monster.model.DepositoRequest;
import ec.edu.monster.model.LoginRequest;
import ec.edu.monster.model.MovimientoModel;
import ec.edu.monster.model.MovimientosRequest;
import ec.edu.monster.services.WSCuenta;
import ec.edu.monster.services.WSLogin;
import ec.edu.monster.services.WSMovimiento;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;

public class MainController {
    private String usuarioActual = null;
    private WSLogin wsLogin = new WSLogin();
    private WSCuenta wsCuenta = new WSCuenta();
    private WSMovimiento wsMovimientos = new WSMovimiento();

    public String getUsuarioActual() {
        return usuarioActual;
    }

    public boolean iniciarSesion(String username, String password) {
    LoginRequest request = null;
        try {
            request = new LoginRequest(username, hashPassword(password));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
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


    public void cerrarSesion() {
        usuarioActual = null;
    }

   public boolean realizarDeposito(String cuenta, String monto) {
    DepositoRequest request = new DepositoRequest(cuenta, monto);

    try {
        String response = wsCuenta.deposito(request, String.class);
        return "true".equalsIgnoreCase(response);
    } catch (Exception e) {
        System.out.println("Error al realizar el depósito: " + e.getMessage());
    }
    return false;
}


public String verMovimientos(String cuenta) {
    if (cuenta == null || cuenta.isEmpty()) {
        return "La cuenta no puede ser nula o vacía.";
    }

    // Crear una instancia de MovimientosRequest con el número de cuenta
    MovimientosRequest request = new MovimientosRequest(cuenta);

    try {
        // Llamar al servicio web para obtener los movimientos
        MovimientoModel[] movimientos = wsMovimientos.getMovimientos(request);

        // Verificar si se obtuvieron movimientos
        if (movimientos == null || movimientos.length == 0) {
            return "No se encontraron movimientos para la cuenta: " + cuenta;
        }

        StringBuilder resultado = new StringBuilder();
        resultado.append("\n===== MOVIMIENTOS DE LA CUENTA ").append(cuenta).append(" =====\n");
        resultado.append(String.format("%-10s %-20s %-15s %-20s %-15s %-15s%n",
                "Nro", "Fecha", "Tipo Mov.", "Descripción", "Importe", "Cta Referencia"));
        resultado.append("------------------------------------------------------------------------\n");

        double totalIngresos = 0.0;
        double totalEgresos = 0.0;

        for (MovimientoModel mov : movimientos) {
            // Sumar ingresos y egresos según el tipo de movimiento
            if (!"Retiro".equals(mov.getTipoDescripcion())) {
                totalIngresos += mov.getImporteMovimiento();
            } else {
                totalEgresos += mov.getImporteMovimiento();
            }

            resultado.append(String.format("%-10d %-20s %-15s %-20s %-15.2f %-15s%n",
                    mov.getNumeroMovimiento(),
                    mov.getFechaMovimiento(),
                    mov.getCodigoTipoMovimiento(),
                    mov.getTipoDescripcion(),
                    mov.getImporteMovimiento(),
                    mov.getCuentaReferencia() != null ? mov.getCuentaReferencia() : "N/A"));
        }

        // Calcular y mostrar el resumen
        resultado.append("\nRESUMEN:\n");
        resultado.append(String.format("Total Ingresos: %.2f%n", totalIngresos));
        resultado.append(String.format("Total Egresos (Retiros): %.2f%n", Math.abs(totalEgresos)));
        resultado.append(String.format("Saldo Neto: %.2f%n", totalIngresos - totalEgresos));

        return resultado.toString();
    } catch (Exception e) {
        return "Error al obtener los movimientos: " + e.getMessage();
    }
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
