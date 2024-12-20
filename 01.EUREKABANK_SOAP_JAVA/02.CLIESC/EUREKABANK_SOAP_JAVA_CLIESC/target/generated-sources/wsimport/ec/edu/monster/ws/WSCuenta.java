
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
@WebService(name = "WSCuenta", targetNamespace = "http://ws.monster.edu.ec/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSCuenta {


    /**
     * 
     * @param cd
     * @param cuenta
     * @param monto
     * @param tipo
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "cuenta", targetNamespace = "http://ws.monster.edu.ec/", className = "ec.edu.monster.ws.Cuenta")
    @ResponseWrapper(localName = "cuentaResponse", targetNamespace = "http://ws.monster.edu.ec/", className = "ec.edu.monster.ws.CuentaResponse")
    @Action(input = "http://ws.monster.edu.ec/WSCuenta/cuentaRequest", output = "http://ws.monster.edu.ec/WSCuenta/cuentaResponse")
    public boolean cuenta(
        @WebParam(name = "cuenta", targetNamespace = "")
        String cuenta,
        @WebParam(name = "monto", targetNamespace = "")
        String monto,
        @WebParam(name = "tipo", targetNamespace = "")
        String tipo,
        @WebParam(name = "cd", targetNamespace = "")
        String cd);

}
