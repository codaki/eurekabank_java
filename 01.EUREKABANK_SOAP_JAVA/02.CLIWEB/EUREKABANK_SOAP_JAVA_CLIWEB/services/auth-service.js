const SoapClient = require('./soapclient');

class AuthService {
    constructor() {
        this.wsdlUrl = 'http://10.40.13.255:8080/EUREKABANK_SOAP_JAVA/WSLogin?wsdl';
    }

    async authenticate(username, password) {
        try {
            const soapClient = new SoapClient(this.wsdlUrl);
            const client = await soapClient.createClient();

            const hashedPassword = SoapClient.hashPassword(password);

            return new Promise((resolve, reject) => {
                client.auth({ username, password: hashedPassword }, (err, result) => {
                    if (err) reject(err);
                    resolve(result.return);
                });
            });
        } catch (error) {
            console.error('Authentication Error:', error);
            throw error;
        }
    }
}

module.exports = new AuthService();