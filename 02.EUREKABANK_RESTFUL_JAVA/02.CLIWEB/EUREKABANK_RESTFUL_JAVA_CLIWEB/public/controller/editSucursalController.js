import { showModal } from "./modal.js";

document.addEventListener("DOMContentLoaded", () => {
  const urlParams = new URLSearchParams(window.location.search);
  const sucursalId = urlParams.get("id");

  if (sucursalId) {
    loadSucursalData(sucursalId);
  }

  document
    .getElementById("editSucursalForm")
    .addEventListener("submit", async (e) => {
      e.preventDefault();

      const nombre = document.getElementById("nombre").value;
      const ciudad = document.getElementById("ciudad").value;
      const direccion = document.getElementById("direccion").value;
      const latitude = parseFloat(document.getElementById("latitude").value);
      const longitude = parseFloat(document.getElementById("longitude").value);

      try {
        const response = await fetch(
          `http://localhost:8002/api/sucursal/${sucursalId}`,
          {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
              nombre,
              ciudad,
              direccion,
              latitude,
              longitude,
            }),
          }
        );

        const result = await response.json();

        if (response.ok) {
          showModal(
            "Sucursal Actualizada",
            "La sucursal ha sido actualizada con éxito.",
            () => {
              window.location.href = "/sucursales";
            }
          );
        } else {
          showModal(
            "Error",
            "No se pudo actualizar la sucursal. Intente de nuevo."
          );
        }
      } catch (error) {
        console.error("Edit Sucursal error:", error);
        showModal(
          "Error",
          "Ocurrió un error durante la actualización. Por favor, revise la conexión."
        );
      }
    });
});

async function loadSucursalData(sucursalId) {
  try {
    const response = await fetch(
      `http://localhost:8002/api/sucursal/${sucursalId}`
    );

    if (!response.ok) {
      throw new Error("Error al cargar los datos de la sucursal");
    }

    const sucursal = await response.json();

    document.getElementById("nombre").value = sucursal.nombre;
    document.getElementById("ciudad").value = sucursal.ciudad;
    document.getElementById("direccion").value = sucursal.direccion;
    document.getElementById("latitude").value = sucursal.latitude;
    document.getElementById("longitude").value = sucursal.longitude;
  } catch (error) {
    console.error("Load Sucursal Data error:", error);
    showModal(
      "Error",
      "Ocurrió un error al cargar los datos de la sucursal. Por favor, revise la conexión."
    );
  }
}
