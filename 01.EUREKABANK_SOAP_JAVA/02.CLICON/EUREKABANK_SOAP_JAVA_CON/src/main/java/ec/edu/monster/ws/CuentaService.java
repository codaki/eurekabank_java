
package ec.edu.monster.ws;

import ec.edu.monster.ws.WSCuenta;
import ec.edu.monster.ws.WSCuenta_Service;
import java.util.List;
public class CuentaService {
    public boolean realizarDeposito (String cuenta, String monto, String tipo, String cd){
        WSCuenta_Service servicio = new WSCuenta_Service();
        WSCuenta port = servicio.getWSCuentaPort();
        return port.cuenta(cuenta, monto, tipo, cd);
    }
}
