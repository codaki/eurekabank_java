
package ec.edu.monster.DAO;

import ec.edu.monster.DBB.DBConnection;
import ec.edu.monster.model.MovimientoModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimientoDAO {

    // Método para obtener movimientos por cuenta
    public List<MovimientoModel> obtenerMovimientosPorCuenta(String codigoCuenta) throws SQLException {
        String sql = "SELECT chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, " +
                     "chr_tipocodigo, dec_moviimporte, chr_cuenreferencia " +
                     "FROM movimiento WHERE chr_cuencodigo = ?";
        List<MovimientoModel> movimientos = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codigoCuenta);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                MovimientoModel movimiento = new MovimientoModel();
                movimiento.setCodigoCuenta(resultSet.getString("chr_cuencodigo"));
                movimiento.setNumeroMovimiento(resultSet.getInt("int_movinumero"));
                movimiento.setFechaMovimiento(resultSet.getDate("dtt_movifecha"));
                movimiento.setCodigoEmpleado(resultSet.getString("chr_emplcodigo"));
                movimiento.setCodigoTipoMovimiento(resultSet.getString("chr_tipocodigo"));
                movimiento.setImporteMovimiento(resultSet.getDouble("dec_moviimporte"));
                movimiento.setCuentaReferencia(resultSet.getString("chr_cuenreferencia"));

                movimientos.add(movimiento);
            }
        }
        return movimientos;
    }

    // Método para registrar un movimiento
    public void registrarMovimiento(MovimientoModel movimiento) throws SQLException {
        String sql = "INSERT INTO movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, " +
                     "chr_tipocodigo, dec_moviimporte, chr_cuenreferencia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, movimiento.getCodigoCuenta());
            statement.setInt(2, movimiento.getNumeroMovimiento());
            statement.setDate(3, new Date(movimiento.getFechaMovimiento().getTime()));
            statement.setString(4, movimiento.getCodigoEmpleado());
            statement.setString(5, movimiento.getCodigoTipoMovimiento());
            statement.setDouble(6, movimiento.getImporteMovimiento());
            statement.setString(7, movimiento.getCuentaReferencia());
            statement.executeUpdate();
        }
    }
}

