import { showModal } from "/controller/modal.js";

document.getElementById("loginForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const username = document.getElementById("Correo").value;
  const password = document.getElementById("password").value;

  // Hashear la contraseña usando CryptoJS

  try {
    const response = await fetch("/loginCliente", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });

    const result = await response.json();

    if (result.success) {
      localStorage.setItem("userData", JSON.stringify(result.success));
      window.location.href = "/cuentas";
    } else {
      showModal("Login Fallido", "Por favor, intente de nuevo.");
    }
  } catch (error) {
    console.error("Login error:", error);
    showModal(
      "Error",
      "Ocurrió un error durante el login. Por favor, revise la conexión."
    );
  }
});
