package ec.edu.monster.model;

public class DepositoRequest {
    private String cuenta;
    private String monto;

    // Constructores, getters y setters
    public DepositoRequest() {}

    public DepositoRequest(String cuenta, String monto) {
        this.cuenta = cuenta;
        this.monto = monto;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
