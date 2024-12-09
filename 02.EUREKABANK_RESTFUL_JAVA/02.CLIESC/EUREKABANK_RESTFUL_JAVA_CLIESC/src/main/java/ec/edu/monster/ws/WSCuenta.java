package ec.edu.monster.ws;

import com.fasterxml.jackson.core.util.JacksonFeature;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:CuentaResource [cuenta]<br>
 * USAGE:
 * <pre>
 *        WSCuenta client = new WSCuenta();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author danie
 */
public class WSCuenta {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://10.40.13.255:8080/EUREKABANK_RESTFUL_JAVA/resources";

    public WSCuenta() {
        client = ClientBuilder.newBuilder()
                .register(JacksonFeature.class) // Registrar Jackson para JSON
                .build();
        webTarget = client.target(BASE_URI).path("cuenta");
    }

    public <T> T deposito(Object requestEntity, Class<T> responseType) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), responseType);
    }

    public void close() {
        client.close();
    }
    
}