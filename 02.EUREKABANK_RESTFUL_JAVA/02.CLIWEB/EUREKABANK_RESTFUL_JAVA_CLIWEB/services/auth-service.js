class AuthService {
  constructor() {
    this.baseUrl = "http://localhost:8001/api/usuario/login";
  }

  async authenticate(username, password) {
    try {
      console.log(username, password);
      const url = `${this.baseUrl}?usuario=${username}&clave=${password}`;

      const response = await fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      const data = await response.json();
      return data;
    } catch (error) {
      console.error("Authentication Error:", error);
      throw error;
    }
  }
}

module.exports = new AuthService();
