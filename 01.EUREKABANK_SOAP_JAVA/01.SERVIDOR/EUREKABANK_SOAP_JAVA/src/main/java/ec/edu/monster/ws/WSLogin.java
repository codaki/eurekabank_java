
package ec.edu.monster.ws;

import ec.edu.monster.service.LoginService;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

@WebService(serviceName = "WSLogin")
public class WSLogin {

    /**
     * This is a sample web service operation
     * @param username
     * @param password
     * @return 
     */
   @WebMethod(operationName = "auth")
    public boolean auth(@WebParam(name = "username") String username,@WebParam(name = "password") String password) {
        LoginService service = new LoginService();
        boolean resultado = service.login(username, password);
        return resultado;
        
    }
}

