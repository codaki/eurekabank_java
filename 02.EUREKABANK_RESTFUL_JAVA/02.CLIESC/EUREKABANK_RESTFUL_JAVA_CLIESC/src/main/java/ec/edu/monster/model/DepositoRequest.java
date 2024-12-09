package ec.edu.monster.model;

public class DepositoRequest {
    private String cuenta;
    private String monto;
    private String tipo;
    private String cd;

    // Constructores, getters y setters
    public DepositoRequest() {}

    public DepositoRequest(String cuenta, String monto, String tipo, String cd) {
        this.cuenta = cuenta;
        this.monto = monto;
        this.tipo = tipo;
        this.cd = cd;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }
    
}
