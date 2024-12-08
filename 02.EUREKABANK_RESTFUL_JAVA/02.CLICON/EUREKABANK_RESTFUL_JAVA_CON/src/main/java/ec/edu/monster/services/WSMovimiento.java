
package ec.edu.monster.services;

import ec.edu.monster.model.MovimientoModel;
import ec.edu.monster.model.MovimientosRequest;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:MovimientoResource
 * [movimiento]<br>
 * USAGE:
 * <pre>
 *        WSMovimiento client = new WSMovimiento();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author danie
 */
public class WSMovimiento {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/EUREKABANK_RESTFUL_JAVA/resources";

    public WSMovimiento() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("movimiento");
    }

    public MovimientoModel[] getMovimientos(MovimientosRequest requestEntity) throws ClientErrorException {
        // Se espera una respuesta JSON que será deserializada a un arreglo de MovimientoModel
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON)
                        .post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), MovimientoModel[].class);
    }

    public void close() {
        client.close();
    }
    
}
