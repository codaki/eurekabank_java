class AuthService {
    constructor() {
        this.baseUrl = 'http://10.40.13.255:8080/EUREKABANK_RESTFUL_JAVA/resources/login';
    }

    async authenticate(username, password) {
        try {
            // Cuerpo de la petición
            const payload = { username, password };

            // Configuración del fetch
            const response = await fetch(this.baseUrl, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            // Parsear la respuesta
            const data = await response.json();
            console.log(data);
            return data; // Retornar el resultado desde el servicio REST
        } catch (error) {
            console.error('Authentication Error:', error);
            throw error;
        }
    }

}

module.exports = new AuthService();
