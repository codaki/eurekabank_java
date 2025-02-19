import { showModal, closeModal } from "./modal.js";

document.addEventListener("DOMContentLoaded", () => {
    const userData = JSON.parse(localStorage.getItem("userData"));

    if (userData) {
        const userInfoButton = document.getElementById("userInfoButton");
        userInfoButton.textContent = `${userData.nombre} ${userData.apellidoPaterno}`;

        console.log(userData);
        userInfoButton.addEventListener("click", () => {
            const userInfoText = `
                Nombre: ${userData.nombre}
                Apellido Paterno: ${userData.apellidoPaterno}
                Apellido Materno: ${userData.apellidoMaterno}
                Correo: ${userData.correo}
            `;
            showModal("Información del Usuario", userInfoText);
        });

        const logoutButton = document.getElementById("logoutButton");
        logoutButton.addEventListener("click", () => {
            localStorage.clear();
            window.location.href = "/loginCliente";
        });
    }
});
