const Cookies = require("js-cookie"); // Add this at the top

class AuthService {
  constructor() {
    this.baseUrl = "http://localhost:8001/api/cliente/login";
  }

  async authenticate(username, password) {
    try {
      console.log(username, password);
      const url = `${this.baseUrl}?email=${username}&password=${password}`;

      const response = await fetch(url, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
      });

      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }

      const data = await response.json();
      // Store in cookie with 7 days expiration
      Cookies.set("clienteData", JSON.stringify(data), { expires: 7 });
      console.log(data);
      return data;
    } catch (error) {
      console.error("Authentication Error:", error);
      throw error;
    }
  }

  getClienteData() {
    const data = Cookies.get("clienteData");
    return data ? JSON.parse(data) : null;
  }

  logout() {
    Cookies.remove("clienteData");
  }
}

module.exports = new AuthService();
