import { showModal } from "./modal.js";

document.addEventListener("DOMContentLoaded", () => {
    loadCuentas();

    const searchButton = document.getElementById("searchButton");
    const searchInput = document.getElementById("accountSearch");

    searchButton.addEventListener("click", () => filterBranches());
    searchInput.addEventListener("keyup", (e) => {
        if (e.key === "Enter") {
            filterBranches();
        }
    });

    searchInput.addEventListener("input", () => filterBranches());
});

async function loadCuentas() {
    const userData = localStorage.getItem("userData");
    console.log(userData);
    //   try {
    //     console.log("Fetching cuentas...");
    //     const response = await fetch("http://localhost:8002/api/sucursal", {
    //       method: "GET",
    //       headers: { "Content-Type": "application/json" },
    //     });
    //     if (!response.ok) {
    //       throw new Error("Network response was not ok");
    //     }
    //     const branches = await response.json();
    //     displayBranches(branches);
    //   } catch (error) {
    //     showModal(
    //       "Error",
    //       "No se pudieron cargar las sucursales: " + error.message
    //     );
    //   }
}