import { showModal } from '/controller/modal.js';

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('searchButton').addEventListener('click', searchMovements);
});

async function searchMovements() {
    const accountNumber = document.getElementById('accountNumber').value;

    try {
        const response = await fetch('/movementsR', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ numcuenta: accountNumber })
        });

        const result = await response.json();

        if (result.success) {
            populateMovements(result.movements);
        } else {
            showModal('Error', 'Fallo en recuperar los movimientos.');
        }
    } catch (error) {
        console.error('Search movements error:', error);
        showModal('Error', 'Un error ha ocurrido durante la búsqueda de movimientos. Por favor, revise la conexión.');
    }
}

function populateMovements(movements) {
    const movementsList = document.getElementById('movements-list');
    movementsList.innerHTML = '';

    movements.forEach(movement => {
        const movementItem = document.createElement('div');
        movementItem.className = 'movement-item';
        movementItem.innerHTML = `
            <div>
                <div class="font-bold text-black">Cuenta: ${movement.cuenta}</div>
                <div class="font-normal text-gray-700">Fecha: ${movement.fecha}</div>
                <div class="font-normal text-gray-700">Número Movimiento: ${movement.numero}</div>
            </div>
            <div>
                <div class="font-normal text-gray-700">Tipo: ${movement.tipo}</div>
                <div class="font-normal text-gray-700">Acción: ${movement.accion}</div>
                <div class="font-bold text-black">Importe: $${movement.importe}</div>
            </div>
        `;
        movementsList.appendChild(movementItem);
    });
}