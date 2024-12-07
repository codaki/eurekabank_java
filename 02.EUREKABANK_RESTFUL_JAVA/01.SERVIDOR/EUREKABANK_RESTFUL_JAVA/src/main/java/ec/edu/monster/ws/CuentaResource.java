
package ec.edu.monster.ws;

import ec.edu.monster.service.CuentaService;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author danie
 */
@Path("cuenta")
@RequestScoped
public class CuentaResource {

    
    public static class DepositoRequest {
    private String cuenta;
    private double monto;

        public DepositoRequest() {
        }

        // Getters and setters
        public String getCuenta() {
            return cuenta;
        }

        public void setCuenta(String cuenta) {
            this.cuenta = cuenta;
        }

        public double getMonto() {
            return monto;
        }

        public void setMonto(double monto) {
            this.monto = monto;
        }
    }

     /**
     * Handles deposit operations.
     * @param request The deposit request object.
     * @return A response indicating success or failure.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deposito(DepositoRequest request) {
        CuentaService servicio = new CuentaService();
        return servicio.actualizarSaldoYRegistrarMovimiento(request.getCuenta(), request.getMonto());
    }
}
