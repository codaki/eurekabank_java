const SoapClient = require('./soapclient');

class CuentaService {
    constructor() {
        this.wsdlUrl = 'http://localhost:8080/EUREKABANK_SOAP_JAVA/WSCuenta?wsdl';
    }

    async deposit(cuenta, monto) {
        try {
            console.log(`Calling SOAP service: cuenta=${cuenta}, monto=${monto}`);
            const soapClient = new SoapClient(this.wsdlUrl);
            const client = await soapClient.createClient();

            return new Promise((resolve, reject) => {
                client.cuenta({ cuenta, monto }, (err, result) => {
                    if (err) {
                        console.error('SOAP call failed:', err);
                        return reject(err);
                    }
                    console.log('SOAP response:', result);
                    resolve(result.return);
                });
            });
        } catch (error) {
            console.error('CuentaService deposit error:', error);
            throw error;
        }
    }

}

module.exports = new CuentaService();