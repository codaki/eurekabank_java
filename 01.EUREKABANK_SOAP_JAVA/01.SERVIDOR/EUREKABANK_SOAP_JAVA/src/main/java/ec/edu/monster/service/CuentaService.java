
package ec.edu.monster.service;


import ec.edu.monster.DAO.CuentaDAO;
import ec.edu.monster.DAO.MovimientoDAO;
import ec.edu.monster.model.MovimientoModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CuentaService {

    private MovimientoDAO movimientoDAO = new MovimientoDAO();
    private CuentaDAO cuentaDAO = new CuentaDAO();

    // Método para actualizar el saldo de la cuenta y registrar el movimiento
    public boolean actualizarSaldoYRegistrarMovimiento(String codigoCuenta,  double valorMovimiento) {
        try {
            // 1. Actualizamos el saldo de la cuenta
            boolean saldoActualizado = cuentaDAO.actualizarSaldoCuenta(codigoCuenta, valorMovimiento);
            if (!saldoActualizado) {
                return false;  // Si no se pudo actualizar el saldo, retornamos false
            }

            // 2. Registramos el movimiento
            // Primero obtenemos el siguiente número de movimiento
            int numeroMovimiento = obtenerSiguienteNumeroMovimiento(codigoCuenta);

            // Creamos el objeto Movimiento
            MovimientoModel movimiento = new MovimientoModel();
            movimiento.setCodigoCuenta(codigoCuenta);
            movimiento.setNumeroMovimiento(numeroMovimiento);
            movimiento.setFechaMovimiento(LocalDate.now().toString());  // Fecha actual
            movimiento.setCodigoEmpleado("0001");  // Puedes modificar esto según el empleado actual
            movimiento.setCodigoTipoMovimiento("003");  // 'DEP', 'RET', etc.
            movimiento.setImporteMovimiento(valorMovimiento);
           

            // 3. Registramos el movimiento en la base de datos
            movimientoDAO.registrarMovimiento(movimiento);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();  // Imprimimos el error en caso de excepción
            return false;
        }
    }

    // Método para obtener el siguiente número de movimiento para una cuenta
    private int obtenerSiguienteNumeroMovimiento(String codigoCuenta) throws SQLException {
        List<MovimientoModel> movimientos = movimientoDAO.obtenerMovimientosPorCuenta(codigoCuenta);
        int numeroMovimiento = 1;  // Empezamos con el número de movimiento 1 por defecto

        // Buscamos el último número de movimiento
        for (MovimientoModel movimiento : movimientos) {
            if (movimiento.getNumeroMovimiento() >= numeroMovimiento) {
                numeroMovimiento = movimiento.getNumeroMovimiento() + 1;
            }
        }
        return numeroMovimiento;
    }
}
