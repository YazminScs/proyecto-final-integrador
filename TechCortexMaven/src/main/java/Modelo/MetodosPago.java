package Modelo;

public class MetodosPago {
    private int pago_id;
    private String pago_nom;

    public MetodosPago() {
    }

    public MetodosPago(int pago_id, String pago_nom) {
        this.pago_id = pago_id;
        this.pago_nom = pago_nom;
    }

    public int getPago_id() {
        return pago_id;
    }

    public void setPago_id(int pago_id) {
        this.pago_id = pago_id;
    }

    public String getPago_nom() {
        return pago_nom;
    }

    public void setPago_nom(String pago_nom) {
        this.pago_nom = pago_nom;
    }

    @Override
    public String toString() {
        return "MetodosPago{" + "pago_id=" + pago_id + ", pago_nom=" + pago_nom + '}';
    }
}