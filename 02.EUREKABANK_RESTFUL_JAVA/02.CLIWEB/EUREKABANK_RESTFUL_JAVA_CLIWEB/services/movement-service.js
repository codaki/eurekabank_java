class MovementService {
    constructor() {
        this.apiUrl = 'http://localhost:8080/EUREKABANK_RESTFUL_JAVA/resources/movimiento';
    }

    async getMovimientos(accountNumber) {
        try {
            const requestBody = {
                numcuenta: accountNumber
            };

            const response = await fetch(this.apiUrl, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(requestBody)
            });

            if (!response.ok) {
                throw new Error('Error en la solicitud de movimientos');
            }

            const movements = await response.json();

            return movements.map(movement => ({
                cuenta: movement.codigoCuenta || movement.cuenta || '',
                fecha: movement.fechaMovimiento || movement.fecha || '',
                numero: movement.numeroMovimiento || movement.numero || 0,
                tipo: movement.tipoDescripcion || movement.tipo || '',
                accion: parseFloat(movement.importeMovimiento) >= 0 ? 'Crédito' : 'Débito',
                importe: parseFloat(movement.importeMovimiento) || 0.0
            }));
        } catch (error) {
            console.error('Error al obtener los movimientos:', error);
            throw error;
        }
    }
}

module.exports = new MovementService();
