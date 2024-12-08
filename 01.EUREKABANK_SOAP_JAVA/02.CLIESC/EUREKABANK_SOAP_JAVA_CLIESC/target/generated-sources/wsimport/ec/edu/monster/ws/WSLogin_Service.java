
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
@WebServiceClient(name = "WSLogin", targetNamespace = "http://ws.monster.edu.ec/", wsdlLocation = "http://localhost:8080/EUREKABANK_SOAP_JAVA/WSLogin?wsdl")
public class WSLogin_Service
    extends Service
{

    private static final URL WSLOGIN_WSDL_LOCATION;
    private static final WebServiceException WSLOGIN_EXCEPTION;
    private static final QName WSLOGIN_QNAME = new QName("http://ws.monster.edu.ec/", "WSLogin");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/EUREKABANK_SOAP_JAVA/WSLogin?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSLOGIN_WSDL_LOCATION = url;
        WSLOGIN_EXCEPTION = e;
    }

    public WSLogin_Service() {
        super(__getWsdlLocation(), WSLOGIN_QNAME);
    }

    public WSLogin_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSLOGIN_QNAME, features);
    }

    public WSLogin_Service(URL wsdlLocation) {
        super(wsdlLocation, WSLOGIN_QNAME);
    }

    public WSLogin_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSLOGIN_QNAME, features);
    }

    public WSLogin_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSLogin_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WSLogin
     */
    @WebEndpoint(name = "WSLoginPort")
    public WSLogin getWSLoginPort() {
        return super.getPort(new QName("http://ws.monster.edu.ec/", "WSLoginPort"), WSLogin.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSLogin
     */
    @WebEndpoint(name = "WSLoginPort")
    public WSLogin getWSLoginPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.monster.edu.ec/", "WSLoginPort"), WSLogin.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSLOGIN_EXCEPTION!= null) {
            throw WSLOGIN_EXCEPTION;
        }
        return WSLOGIN_WSDL_LOCATION;
    }

}
