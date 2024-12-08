const SoapClient = require('./soapclient');

class MovementService {
    constructor() {
        this.wsdlUrl = 'http://localhost:8080/EUREKABANK_SOAP_JAVA/WSMovimiento?wsdl';
        this.namespace = 'http://ws.monster.edu.ec/';
        this.methodName = 'hello';
    }

    async getMovimientos(accountNumber) {
        try {
            const soapClient = new SoapClient(this.wsdlUrl);
            const client = await soapClient.createClient();

            return new Promise((resolve, reject) => {
                // Prepare the input parameters matching the Java implementation
                const args = {
                    numcuenta: accountNumber
                };

                // Call the SOAP method with correct namespace and method name
                client[this.methodName](args, (err, result, raw, soapHeader) => {
                    // Log all available information for debugging
                    console.log('SOAP Response Error:', err);
                    console.log('SOAP Raw Result:', raw);
                    console.log('SOAP Result:', result);
                    console.log('SOAP Header:', soapHeader);

                    if (err) {
                        console.error('SOAP Request Error:', err);
                        return reject(err);
                    }

                    try {
                        // Additional null/undefined checks
                        if (!result) {
                            console.error('No result returned from SOAP service');
                            return reject(new Error('No result returned'));
                        }

                        // More flexible result processing
                        const movements = result.return
                            ? (Array.isArray(result.return) ? result.return : [result.return])
                            : [];

                        const processedMovements = movements.map(movement => {
                            // Log each movement for debugging
                            console.log('Raw Movement:', movement);

                            return {
                                cuenta: movement.codigoCuenta || movement.cuenta || '',
                                fecha: movement.fechaMovimiento || movement.fecha || '',
                                numero: movement.numeroMovimiento || movement.numero || 0,
                                tipo: movement.tipoDescripcion || movement.tipo || '',
                                accion: movement.codigoTipoMovimiento || movement.tipo || '',
                                accion: parseFloat(movement.importeMovimiento) >= 0 ? 'Crédito' : 'Débito',
                                importe: parseFloat(movement.importeMovimiento) || parseFloat(movement.importe) || 0.0
                            };
                        });

                        resolve(processedMovements);
                    } catch (parseError) {
                        console.error('Result Parsing Error:', parseError);
                        reject(parseError);
                    }
                });
            });
        } catch (error) {
            console.error('Account Movements Error:', error);
            throw error;
        }
    }
}

module.exports = new MovementService();