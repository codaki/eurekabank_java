package ec.edu.monster.service;

import ec.edu.monster.DAO.MovimientoDAO;
import ec.edu.monster.model.MovimientoModel;
import java.sql.SQLException;
import java.util.List;

public class MovimientoService {
    MovimientoDAO DAO = new MovimientoDAO();
    public List<MovimientoModel> ObtenerMovimiento(String cuenta) throws SQLException{
        return DAO.obtenerMovimientosPorCuenta(cuenta);
    }         
}
