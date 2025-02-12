class AuthService {
  constructor() {
    this.baseUrl = "http://localhost:8001/api/usuario/login";
  }

  async authenticate(username, password) {
    try {
      console.log(username, password);
      const url = `${this.baseUrl}?usuario=${username}&clave=${password}`;

      // Configuración del fetch
      const response = await fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        // Ya no necesitamos el body porque los parámetros van en la URL
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      // Parsear la respuesta
      const data = await response.json();
      console.log(data);
      return data;
    } catch (error) {
      console.error("Authentication Error:", error);
      throw error;
    }
  }
}

module.exports = new AuthService();
