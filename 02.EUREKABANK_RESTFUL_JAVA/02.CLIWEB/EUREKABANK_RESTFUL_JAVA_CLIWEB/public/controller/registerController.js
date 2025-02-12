import { showModal } from "/controller/modal.js";

document
  .getElementById("registerForm")
  .addEventListener("submit", async (e) => {
    e.preventDefault();

    const apellidoPaterno = document.getElementById("apellidoPaterno").value;
    const apellidoMaterno = document.getElementById("apellidoMaterno").value;
    const nombre = document.getElementById("nombre").value;
    const dni = document.getElementById("dni").value;
    const ciudad = document.getElementById("ciudad").value;
    const direccion = document.getElementById("direccion").value;
    const telefono = document.getElementById("telefono").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {
      const response = await fetch(
        "http://localhost:8001/api/cliente/register",
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({
            apellidoPaterno,
            apellidoMaterno,
            nombre,
            dni,
            ciudad,
            direccion,
            telefono,
            email,
            password,
          }),
        }
      );

      const result = await response.json();
      console.log(result);
      console.log(response);
      if (response.ok === true) {
        showModal(
          "Registro Exitoso",
          "El cliente ha sido registrado con éxito.",
          () => {
            window.location.href = "/loginCliente";
          }
        );
      } else {
        showModal("Registro Fallido", "Por favor, intente de nuevo.");
      }
    } catch (error) {
      console.error("Register error:", error);
      showModal(
        "Error",
        "Ocurrió un error durante el registro. Por favor, revise la conexión."
      );
    }
  });
