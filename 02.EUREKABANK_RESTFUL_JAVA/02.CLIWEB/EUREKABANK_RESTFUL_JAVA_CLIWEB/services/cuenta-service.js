class CuentaService {
    constructor() {
        this.apiUrl = 'http://localhost:8080/EUREKABANK_RESTFUL_JAVA/resources/cuenta';
    }

    async deposit(cuenta, monto) {
        try {
            const requestBody = {
                cuenta: cuenta,
                monto: monto
            };

            const response = await fetch(`${this.apiUrl}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(requestBody)
            });

            if (!response.ok) {
                throw new Error('Error al realizar el depósito');
            }

            // Aquí estamos asumiendo que la respuesta será un string "true" o "false"
            const result = await response.text();  // Usamos text en lugar de json
            if (result.toLowerCase() === 'true') {
                console.log('Depósito realizado con éxito');
                return { success: true };
            } else {
                console.error('No se pudo realizar el depósito');
                return { success: false };
            }
        } catch (error) {
            console.error('Error al realizar el depósito:', error);
            throw error;
        }
    }
}

module.exports = new CuentaService();
