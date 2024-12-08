const soap = require('soap');
const crypto = require('crypto');

class SoapClient {
    constructor(wsdlUrl) {
        this.wsdlUrl = wsdlUrl;
    }

    async createClient() {
        return new Promise((resolve, reject) => {
            soap.createClient(this.wsdlUrl, (err, client) => {
                if (err) reject(err);
                resolve(client);
            });
        });
    }

    static hashPassword(password) {
        return crypto.createHash('sha256').update(password).digest('hex');
    }
}

module.exports = SoapClient;