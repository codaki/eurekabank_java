import { showModal } from "./modal.js";

document.addEventListener("DOMContentLoaded", () => {
    loadCuentas();

    const searchButton = document.getElementById("searchButton");
    const searchInput = document.getElementById("accountSearch");

    searchButton.addEventListener("click", () => filterCuentas());
    searchInput.addEventListener("keyup", (e) => {
        if (e.key === "Enter") {
            filterCuentas();
        }
    });

    searchInput.addEventListener("input", () => filterCuentas());
});

async function loadCuentas() {
    const userData = JSON.parse(localStorage.getItem("userData"));
    try {
        console.log("Fetching cuentas...");
        const response = await fetch(`http://localhost:8003/api/cuenta/cliente/${userData.codigo}`, {
            method: "GET",
            headers: { "Content-Type": "application/json" },
        });
        if (!response.ok) {
            throw new Error("Network response was not ok");
        }
        const cuentas = await response.json();
        displayCuentas(cuentas);
    } catch (error) {
        showModal(
            "Error",
            "No se pudieron cargar las sucursales: " + error.message
        );
    }
}

function displayCuentas(cuentas) {
    const cuentasList = document.getElementById("cuentas-list");
    cuentasList.innerHTML = "";

    cuentas.forEach((cuenta) => {
        const cuentaElement = document.createElement("div");
        cuentaElement.className = "cuenta-item";
        cuentaElement.innerHTML = `
              <h3>Número de cuenta: ${cuenta.codigo}</h3>
              <div class="cuenta-details">
                <div class="cuenta-info">
                    <p><strong>Saldo:</strong> ${cuenta.monedaNombre == "Dolares" ? "$" + cuenta.saldo : cuenta.saldo}</p>
                    <p><strong>Moneda:</strong> ${cuenta.monedaNombre == "Dolares" ? "Dólares" : cuenta.monedaNombre}</p>
                    <p><strong>Empleado asesor:</strong> ${cuenta.empleadoNombre}</p>
                    <p><strong>Estado:</strong> ${cuenta.estado}</p>
                    <p><strong>Sucursal:</strong> ${cuenta.sucursalNombre}</p>
                    <p><strong>Movimientos:</strong> ${cuenta.contadorMovimientos}</p>
                    <p><strong>Fecha de Creación:</strong> ${cuenta.fechaCreacion}</p>
                </div>
                <div class="cuenta-actions">
                    <button class="btn btn-primary movimientos-button" data-codigo="${cuenta.codigo}">Movimientos</button>
                    <button class="btn btn-primary transferencia-button" data-codigo="${cuenta.codigo}">Realizar Transferencia</button>
                </div>
              </div>
          `;
        cuentasList.appendChild(cuentaElement);
    });

    // Add event listeners for the buttons
    document.querySelectorAll(".movimientos-button").forEach(button => {
        button.addEventListener("click", (e) => {
            const codigo = e.target.getAttribute("data-codigo");
            handleMovimientosClick(codigo);
        });
    });

    document.querySelectorAll(".transferencia-button").forEach(button => {
        button.addEventListener("click", (e) => {
            const codigo = e.target.getAttribute("data-codigo");
            handleTransferenciaClick(codigo);
        });
    });
}

async function handleMovimientosClick(codigo) {
    console.log("Movimientos button clicked for cuenta:", codigo);
    try {
        const response = await fetch('/movementsR', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ numcuenta: codigo })
        });

        const result = await response.json();

        if (result.success) {
            let movementsHtml = '';
            if (result.movements.length > 0) {
                movementsHtml = result.movements.map(movement => `
                    <div class="movement-item-modal">
                        <div>
                            <p><strong>Cuenta:</strong> ${movement.cuenta}</p>
                            <p><strong>Fecha:</strong> ${movement.fecha}</p>
                            <p><strong>Número Movimiento:</strong> ${movement.numero}</p>
                        </div>
                        <div>
                            <p><strong>Tipo:</strong> ${movement.tipo}</p>
                            <p><strong>Acción:</strong> ${movement.accion}</p>
                            <p><strong>Importe:</strong> $${movement.importe}</p>
                        </div>
                    </div>
                    <hr>
                `).join('');
            } else {
                movementsHtml = '<p>No hay movimientos para esta cuenta.</p>';
            }

            showModal('Movimientos', `<div class="movements-list-modal">${movementsHtml}</div>`);
        } else {
            showModal('Error', 'Fallo en recuperar los movimientos.');
        }
    } catch (error) {
        console.error('Search movements error:', error);
        showModal('Error', 'Un error ha ocurrido durante la búsqueda de movimientos. Por favor, revise la conexión.');
    }
}


function handleTransferenciaClick(codigo) {
    console.log("Realizar Transferencia button clicked for cuenta:", codigo);
    window.location.href = `/account.html?cuenta=${codigo}`;
}

function filterCuentas() {
    const searchText = document
        .getElementById("accountSearch")
        .value.toLowerCase();
    const cuentaItems = document.querySelectorAll(".cuenta-item");

    cuentaItems.forEach((item) => {
        const text = item.textContent.toLowerCase();
        item.style.display = text.includes(searchText) ? "" : "none";
    });
}