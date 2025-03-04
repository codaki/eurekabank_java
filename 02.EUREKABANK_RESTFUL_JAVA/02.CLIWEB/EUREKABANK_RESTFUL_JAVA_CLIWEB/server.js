const express = require("express");
const cors = require('cors');
const bodyParser = require("body-parser");
const path = require("path");
const authServiceCliente = require("./services/auth-serviceCliente");
const authService = require("./services/auth-service");
const MovementService = require("./services/movement-service");
const CuentaService = require("./services/cuenta-service");
const app = express();
const PORT = process.env.PORT || 3001;

app.use(cors());

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
app.get("/cuentas", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "cuentas.html"));
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

app.get("/createSucursal", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "createSucursal.html"));
});
app.get("/mapaSucursales", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "mapSucursales.html"));
});
app.get("/editSucursal", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "editSucursal.html"));
});
app.get("/register", (req, res) => {
  res.sendFile(path.join(__dirname, "views", "register.html"));
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

// Proxy endpoint for fetching news
app.get("/api/news", async (req, res) => {
  try {
    const fetch = (await import('node-fetch')).default;
    const response = await fetch('https://api.apitube.io/v1/news/everything?per_page=5&source.country.code=ec&topic.id=industry.financial_news&language.code=es&api_key=api_live_a8dUedYyczp9PEUBecwVpH1S21Y6Oqs2gJlFCs2zhWdaByfAyJGQD6uqsBnq');
    const data = await response.json();
    res.json(data);
  } catch (error) {
    console.error('Error fetching news:', error);
    res.status(500).json({ success: false, message: 'Error fetching news' });
  }
});

app.get("/api/exchange", async (req, res) => {
  try {
    const fetch = (await import('node-fetch')).default;
    const response = await fetch('https://openexchangerates.org/api/latest.json?app_id=2be36eda6412460a9f563f2b1a109dfe&symbols=EUR,GBP,JPY,CAD,AUD,CHF,CNY,INR,BRL,MXN'); // Replace YOUR_APP_ID with your actual app ID
    const data = await response.json();
    res.json(data);
  } catch (error) {
    console.error('Error fetching exchange rates:', error);
    res.status(500).json({ success: false, message: 'Error fetching exchange rates' });
  }
});

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});