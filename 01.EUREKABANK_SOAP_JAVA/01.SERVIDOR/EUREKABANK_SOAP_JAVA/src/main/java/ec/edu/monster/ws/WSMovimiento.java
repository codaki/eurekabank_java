
package ec.edu.monster.ws;

import ec.edu.monster.model.MovimientoModel;
import ec.edu.monster.service.MovimientoService;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebService(serviceName = "WSMovimiento")
public class WSMovimiento {

    /**
     * This is a sample web service operation
     * @param numcuenta
     * @return 
     */
    @WebMethod(operationName = "hello")
    public List<MovimientoModel> movimientos(@WebParam(name = "numcuenta") String numcuenta) {
        MovimientoService servicio = new MovimientoService();
        try {
            return servicio.ObtenerMovimiento(numcuenta);
        } catch (SQLException ex) {
            Logger.getLogger(WSMovimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
