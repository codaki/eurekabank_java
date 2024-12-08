package ec.edu.monster.controller;

import ec.edu.monster.model.MovimientoModel;
import ec.edu.monster.model.MovimientosRequest;
import ec.edu.monster.view.MovimientoView;
import ec.edu.monster.ws.WSMovimiento;

import javax.swing.*;
import java.awt.*;

public class MovimientoController {

    private WSMovimiento wsMovimientos;

    public MovimientoController() {
        this.wsMovimientos = new WSMovimiento();
    }

    /**
     * Método para cargar y mostrar los movimientos en la vista gráfica.
     *
     * @param cuenta Número de cuenta a buscar.
     * @param movimientoView Instancia de la vista donde se mostrarán los resultados.
     */
    public void cargarMovimientos(String cuenta, MovimientoView movimientoView) {
        if (cuenta == null || cuenta.isEmpty()) {
            JOptionPane.showMessageDialog(movimientoView,
                    "La cuenta no puede ser nula o vacía.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Crear una instancia de MovimientosRequest con el número de cuenta
            MovimientosRequest request = new MovimientosRequest(cuenta);
            MovimientoModel[] movimientos = wsMovimientos.getMovimientos(request);

            JPanel panelResultados = new JPanel();
            panelResultados.setLayout(new BoxLayout(panelResultados, BoxLayout.Y_AXIS));
            panelResultados.setBackground(Color.WHITE);

            if (movimientos == null || movimientos.length == 0) {
                JLabel noResultados = new JLabel("No se encontraron movimientos para la cuenta: " + cuenta);
                noResultados.setFont(new Font("Arial", Font.BOLD, 16));
                noResultados.setForeground(Color.RED);
                panelResultados.add(noResultados);
            } else {
                double totalIngresos = 0.0;
                double totalEgresos = 0.0;

                // Crear celdas para cada movimiento
                for (MovimientoModel mov : movimientos) {
                    if (!"Retiro".equals(mov.getTipoDescripcion())) {
                        totalIngresos += mov.getImporteMovimiento();
                    } else {
                        totalEgresos += mov.getImporteMovimiento();
                    }

                    JPanel celda = movimientoView.crearCelda(
                            "Cuenta: " + mov.getCodigoCuenta(),
                            "Fecha: " + mov.getFechaMovimiento(),
                            "Movimiento: " + mov.getNumeroMovimiento(),
                            
                            "Descripción: " + mov.getTipoDescripcion(),
                            "Tipo Mov.: " + mov.getCodigoTipoMovimiento(),
                            "Importe: $" + String.format("%.2f", mov.getImporteMovimiento())
                    );
                    panelResultados.add(celda);
                }

                // Resumen de totales
                JPanel resumenPanel = new JPanel(new GridLayout(3, 1));
                resumenPanel.setBackground(new Color(255, 255, 255));
                resumenPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                JLabel totalIngresosLabel = new JLabel("Total Ingresos: $" + String.format("%.2f", totalIngresos));
                JLabel totalEgresosLabel = new JLabel("Total Egresos (Retiros): $" + String.format("%.2f", Math.abs(totalEgresos)));
                JLabel saldoNetoLabel = new JLabel("Saldo Neto: $" + String.format("%.2f", totalIngresos - totalEgresos));

                resumenPanel.add(totalIngresosLabel);
                resumenPanel.add(totalEgresosLabel);
                resumenPanel.add(saldoNetoLabel);

                panelResultados.add(resumenPanel);
            }

            // Reemplazar el contenido del JScrollPane con los nuevos resultados
            movimientoView.getjScrollPane1().setViewportView(panelResultados);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(movimientoView,
                    "Error al obtener los movimientos: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
