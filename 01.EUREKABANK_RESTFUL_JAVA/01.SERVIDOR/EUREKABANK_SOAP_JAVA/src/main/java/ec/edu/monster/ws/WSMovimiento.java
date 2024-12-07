
package ec.edu.monster.ws;

import ec.edu.monster.model.MovimientoModel;
import ec.edu.monster.service.MovimientoService;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.sql.SQLException;
import java.util.List;


@WebService(serviceName = "WSMovimiento")
public class WSMovimiento {

    /**
     * This is a sample web service operation
     * @param numcuenta
     * @return 
     */
    @WebMethod(operationName = "hello")
    public List<MovimientoModel> movimientos(@WebParam(name = "numcuenta") String numcuenta) throws SQLException {
        MovimientoService servicio = new MovimientoService();
        return servicio.ObtenerMovimiento(numcuenta);
    }
}
