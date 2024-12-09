package ec.edu.monster.controller;

import ec.edu.monster.view.CuentaView;
import ec.edu.monster.model.DepositoRequest;
import ec.edu.monster.ws.WSCuenta;

import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.UIManager;

public class CuentaController {

    private WSCuenta wsCuenta;

    public CuentaController() {
        this.wsCuenta = new WSCuenta();
    }

    /**
     * Procesar un depósito interactuando con la interfaz gráfica.
     *
     * @param cuenta      Número de cuenta al que se realizará el depósito.
     * @param monto       Monto a depositar.
     * @param cuentaView  Vista asociada al depósito.
     */
    public void procesarDeposito(String cuenta, String monto, String tipo, String cd, CuentaView cuentaView) {

        if (cuenta == null || cuenta.isEmpty() || monto == null || monto.isEmpty()) {
            mostrarMensaje("Por favor, complete todos los campos.", "Campos Vacíos");
            return;
        }

        if (!esNumeroValido(monto)) {
            mostrarMensaje("El monto debe ser un número válido mayor a 0.", "Error en el Monto");
            return;
        }

        DepositoRequest request = new DepositoRequest(cuenta, monto, tipo, cd);

        try {
            String response = wsCuenta.deposito(request, String.class);

            if ("true".equalsIgnoreCase(response)) {
                mostrarMensaje("Transacción realizada exitosamente.", "Éxito");
                cuentaView.dispose();
            } else {
                mostrarMensaje("No se pudo realizar la transacción. Verifique los datos ingresados.", "Error en el Depósito");
            }

        } catch (Exception e) {
            mostrarMensaje("Error al realizar la transacción: " + e.getMessage(), "Error");
        }
    }

    /**
     * Mostrar un mensaje en un cuadro de diálogo personalizado.
     *
     * @param mensaje Mensaje a mostrar.
     * @param titulo  Título del cuadro de diálogo.
     */
    private void mostrarMensaje(String mensaje, String titulo) {
        UIManager.put("OptionPane.background", new Color(0, 102, 204));
        UIManager.put("Panel.background", new Color(0, 102, 204));
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Validar si un monto ingresado es un número válido mayor a 0.
     *
     * @param monto Cadena que representa el monto.
     * @return true si el monto es válido, false en caso contrario.
     */
    private boolean esNumeroValido(String monto) {
        try {
            double valor = Double.parseDouble(monto);
            return valor > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
