import { showModal } from '/controller/modal.js';

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('actionButton').addEventListener('click', makeDeposit);
});

async function makeDeposit() {
    const transactionType = document.getElementById('transactionType').value;
    let cuenta, monto, cd;

    if (transactionType === 'TRA') {
        cuenta = document.getElementById('cuentaOrigen').value.trim();
        const cuentaDestino = document.getElementById('cuentaDestino').value.trim();
        monto = parseFloat(document.getElementById('montoTransfer').value.trim());
        cd = cuentaDestino;
    } else {
        cuenta = document.getElementById('cuenta').value.trim();
        monto = parseFloat(document.getElementById('monto').value.trim());
        cd = null;
    }

    if (!cuenta || isNaN(monto) || monto <= 0 || (transactionType === 'TRA' && !cd)) {
        showModal('Error', 'Por favor, ingrese todos los campos requeridos y un monto válido.');
        return;
    }

    try {
        const response = await fetch('/deposit', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ cuenta, monto, tipo: transactionType, cd })
        });

        const result = await response.json();

        if (result.success) {
            showModal('Éxito', 'Operación realizada con éxito.', () => {
                window.location.href = '/movements';
            });
        } else {
            showModal('Error', `Operación fallida: ${result.message}`);
        }
    } catch (error) {
        console.error('Error en la operación:', error);
        showModal('Error', 'Un error ocurrió durante la operación. Por favor, revise la conexión');
    }
}