import { showModal } from '/controller/modal.js';

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('depositButton').addEventListener('click', makeDeposit);
});


async function makeDeposit() {
    const cuenta = document.getElementById('cuenta').value.trim();
    const monto = parseFloat(document.getElementById('monto').value.trim());

    if (!cuenta || isNaN(monto) || monto <= 0) {
        showModal('Error', 'Por favor, ingrese una cuenta y un monto válido.');
        return;
    }

    try {
        const response = await fetch('/deposit', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ cuenta, monto })
        });

        const result = await response.json();

        if (result.success) {
            if (result.result) {
                showModal('Éxito', 'Depósito realizado con éxito.', () => {
                    window.location.href = '/movements';
                });
            } else {
                showModal('Error', 'Fallo al depositar. Verifique que la cuenta exista y tenga saldo suficiente.');
            }
        } else {
            showModal('Error', `Depósito fallido: ${result.message}`);
        }
    } catch (error) {
        console.error('Error en el depósito:', error);
        showModal('Error', 'Un error ocurrió durante el depósito. Por favor, revise la conexión');
    }
}