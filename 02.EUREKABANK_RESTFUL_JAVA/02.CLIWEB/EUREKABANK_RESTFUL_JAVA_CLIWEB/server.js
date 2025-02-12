const express = require("express");
const bodyParser = require("body-parser");
const path = require("path");
const authServiceCliente = require("./services/auth-serviceCliente");
const authService = require("./services/auth-service");
const MovementService = require("./services/movement-service");
const CuentaService = require("./services/cuenta-service");
const app = express();
const PORT = process.env.PORT || 3000;

// Middleware
app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, "public")));
app.use(express.static(path.join(__dirname, "views")));

// Routes
app.get("/", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "login.html"));
});

app.post("/login", async (req, res) => {
  const { username, password } = req.body;
  try {
    const isAuthenticated = await authService.authenticate(username, password);
    res.json({ success: isAuthenticated });
  } catch (error) {
    res.status(500).json({ success: false, message: "Authentication error" });
  }
});

app.post("/loginCliente", async (req, res) => {
  const { username, password } = req.body;
  try {
    const isAuthenticated = await authServiceCliente.authenticate(
      username,
      password
    );
    res.json({ success: isAuthenticated });
  } catch (error) {
    res.status(500).json({ success: false, message: "Authentication error" });
  }
});

app.get("/account", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "account.html"));
});

app.get("/sucursales", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "sucursales.html"));
});
app.get("/loginCliente", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "loginCliente.html"));
});

app.get("/movements", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "movements.html"));
});

app.post("/deposit", async (req, res) => {
  const { cuenta, monto, tipo, cd } = req.body;

  console.log(`Received deposit request: cuenta=${cuenta}, monto=${monto}`);

  try {
    if (!cuenta || !monto) {
      throw new Error("Missing required fields: 'cuenta' or 'monto'");
    }

    const result = await CuentaService.deposit(cuenta, monto, tipo, cd);
    console.log("SOAP service result:", result);

    res.json({ success: true, result });
  } catch (error) {
    console.error("Error in /deposit route:", error);

    res.status(500).json({
      success: false,
      message: "Deposit error",
      error: error.message,
    });
  }
});

app.post("/movementsR", async (req, res) => {
  const { numcuenta } = req.body; // Changed from accountNumber to match your client-side code
  console.log("Received account number:", numcuenta);

  try {
    const movements = await MovementService.getMovimientos(numcuenta);
    res.json({ success: true, movements });
  } catch (error) {
    console.error("Full Movement Retrieval Error:", error);
    res.status(500).json({
      success: false,
      message: "Movement retrieval error",
      error: error.message,
      details: error.toString(),
    });
  }
});
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
