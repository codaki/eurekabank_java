
package ec.edu.monster.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.1
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "WSCuenta", targetNamespace = "http://ws.monster.edu.ec/", wsdlLocation = "http://localhost:8080/EUREKABANK_SOAP_JAVA/WSCuenta?wsdl")
public class WSCuenta_Service
    extends Service
{

    private static final URL WSCUENTA_WSDL_LOCATION;
    private static final WebServiceException WSCUENTA_EXCEPTION;
    private static final QName WSCUENTA_QNAME = new QName("http://ws.monster.edu.ec/", "WSCuenta");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/EUREKABANK_SOAP_JAVA/WSCuenta?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSCUENTA_WSDL_LOCATION = url;
        WSCUENTA_EXCEPTION = e;
    }

    public WSCuenta_Service() {
        super(__getWsdlLocation(), WSCUENTA_QNAME);
    }

    public WSCuenta_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSCUENTA_QNAME, features);
    }

    public WSCuenta_Service(URL wsdlLocation) {
        super(wsdlLocation, WSCUENTA_QNAME);
    }

    public WSCuenta_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSCUENTA_QNAME, features);
    }

    public WSCuenta_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSCuenta_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WSCuenta
     */
    @WebEndpoint(name = "WSCuentaPort")
    public WSCuenta getWSCuentaPort() {
        return super.getPort(new QName("http://ws.monster.edu.ec/", "WSCuentaPort"), WSCuenta.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSCuenta
     */
    @WebEndpoint(name = "WSCuentaPort")
    public WSCuenta getWSCuentaPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.monster.edu.ec/", "WSCuentaPort"), WSCuenta.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSCUENTA_EXCEPTION!= null) {
            throw WSCUENTA_EXCEPTION;
        }
        return WSCUENTA_WSDL_LOCATION;
    }

}