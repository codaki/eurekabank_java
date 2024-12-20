
package ec.edu.monster.ws;

import ec.edu.monster.service.CuentaService;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

@WebService(serviceName = "WSCuenta")
public class WSCuenta {

    /**
     * This is a sample web service operation
     * @param cuenta
     * @param monto
     * @return 
     */
    @WebMethod(operationName = "cuenta")
    public boolean deposito(@WebParam(name = "cuenta") String cuenta,@WebParam(name = "monto") String monto, @WebParam(name = "tipo") String tipo, @WebParam(name = "cd") String cd) {
       CuentaService servicio = new CuentaService();
       return servicio.actualizarSaldoYRegistrarMovimiento(cuenta, monto, tipo, cd);
    }
}
