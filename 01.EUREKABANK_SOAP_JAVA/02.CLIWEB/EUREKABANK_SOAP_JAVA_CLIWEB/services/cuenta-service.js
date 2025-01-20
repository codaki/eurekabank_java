const SoapClient = require("./soapclient");

class CuentaService {
    constructor() {
        this.wsdlUrl = "http://10.40.25.181:8080/EUREKABANK_SOAP_JAVA/WSCuenta?wsdl";
    }

    async deposit(cuenta, monto, tipo, cd) {
        try {
            console.log(
                `Calling SOAP service: cuenta = ${cuenta}, monto = ${monto}, tipo = ${tipo}, cd = ${cd}`
            );
            const soapClient = new SoapClient(this.wsdlUrl);
            const client = await soapClient.createClient();

            return new Promise((resolve, reject) => {
                client.cuenta({ cuenta, monto, tipo, cd }, (err, result) => {
                    if (err) {
                        console.error("SOAP call failed:", err);
                        return reject(err);
                    }
                    console.log("SOAP response:", result);
                    resolve(result.return);
                });
            });
        } catch (error) {
            console.error("CuentaService deposit error:", error);
            throw error;
        }
    }
}

module.exports = new CuentaService();