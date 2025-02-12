import { showModal } from "./modal.js";

document
  .getElementById("createSucursalForm")
  .addEventListener("submit", async (e) => {
    e.preventDefault();

    const nombre = document.getElementById("nombre").value;
    // const codigo = document.getElementById("codigo").value;
    const ciudad = document.getElementById("ciudad").value;
    const direccion = document.getElementById("direccion").value;
    const cuentaContable = 1;
    const latitude = parseFloat(document.getElementById("latitude").value);
    const longitude = parseFloat(document.getElementById("longitude").value);

    try {
      const response = await fetch("http://localhost:8002/api/sucursal", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          nombre,
          ciudad,
          direccion,
          cuentaContable,
          latitude,
          longitude,
        }),
      });

      const result = await response.json();

      if (response.ok) {
        showModal(
          "Sucursal Creada",
          "La sucursal ha sido creada con éxito.",
          () => {
            window.location.href = "/sucursales";
          }
        );
      } else {
        showModal("Error", "No se pudo crear la sucursal. Intente de nuevo.");
      }
    } catch (error) {
      console.error("Create Sucursal error:", error);
      showModal(
        "Error",
        "Ocurrió un error durante la creación. Por favor, revise la conexión."
      );
    }
  });
