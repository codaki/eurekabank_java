import { showModal } from "./modal.js";

document.addEventListener("DOMContentLoaded", () => {
  loadBranches();

  const searchButton = document.getElementById("searchButton");
  const searchInput = document.getElementById("branchSearch");

  searchButton.addEventListener("click", () => filterBranches());
  searchInput.addEventListener("keyup", (e) => {
    if (e.key === "Enter") {
      filterBranches();
    }
  });

  searchInput.addEventListener("input", () => filterBranches());
});

async function loadBranches() {
  try {
    console.log("Fetching branches...");
    const response = await fetch("http://localhost:8002/api/sucursal", {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    });
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const branches = await response.json();
    displayBranches(branches);
  } catch (error) {
    showModal(
      "Error",
      "No se pudieron cargar las sucursales: " + error.message
    );
  }
}

function displayBranches(branches) {
  const branchesList = document.getElementById("branches-list");
  branchesList.innerHTML = "";

  branches.forEach((branch) => {
    const branchElement = document.createElement("div");
    branchElement.className = "branch-item";
    branchElement.innerHTML = `
            <h3>${branch.nombre}</h3>
            <div>
              <p><strong>Código:</strong> ${branch.codigo}</p>
              <p><strong>Ciudad:</strong> ${branch.ciudad}</p>
              <p><strong>Dirección:</strong> ${branch.direccion}</p>
              <p><strong>Cuenta Contable:</strong> ${branch.cuentaContable}</p>
            </div>
        `;
    branchesList.appendChild(branchElement);
  });
}

function filterBranches() {
  const searchText = document
    .getElementById("branchSearch")
    .value.toLowerCase();
  const branchItems = document.querySelectorAll(".branch-item");

  branchItems.forEach((item) => {
    const text = item.textContent.toLowerCase();
    item.style.display = text.includes(searchText) ? "" : "none";
  });
}
