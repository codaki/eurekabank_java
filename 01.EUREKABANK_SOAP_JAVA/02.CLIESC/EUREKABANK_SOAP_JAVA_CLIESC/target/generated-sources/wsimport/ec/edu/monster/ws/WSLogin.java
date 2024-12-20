
package ec.edu.monster.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.1
 * Generated source version: 3.0
 * 
 */
@WebService(name = "WSLogin", targetNamespace = "http://ws.monster.edu.ec/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSLogin {


    /**
     * 
     * @param password
     * @param username
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "auth", targetNamespace = "http://ws.monster.edu.ec/", className = "ec.edu.monster.ws.Auth")
    @ResponseWrapper(localName = "authResponse", targetNamespace = "http://ws.monster.edu.ec/", className = "ec.edu.monster.ws.AuthResponse")
    @Action(input = "http://ws.monster.edu.ec/WSLogin/authRequest", output = "http://ws.monster.edu.ec/WSLogin/authResponse")
    public boolean auth(
        @WebParam(name = "username", targetNamespace = "")
        String username,
        @WebParam(name = "password", targetNamespace = "")
        String password);

}
