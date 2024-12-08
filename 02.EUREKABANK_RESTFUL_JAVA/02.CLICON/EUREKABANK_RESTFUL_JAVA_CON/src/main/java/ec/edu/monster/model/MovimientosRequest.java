package ec.edu.monster.model;

public class MovimientosRequest {
    private String numcuenta;

    // Constructores, getters y setters
    public MovimientosRequest() {}

    public MovimientosRequest(String numcuenta) {
        this.numcuenta = numcuenta;
    }

    public String getNumcuenta() {
        return numcuenta;
    }

    public void setNumcuenta(String numcuenta) {
        this.numcuenta = numcuenta;
    }

   
}
