package Modelo;

public class Orden {

    private int orden_id;
    private Carrito carrito;
    private MetodosPago pago;
    private String orden_estado;

    public Orden() {
    }

    public Orden(int orden_id, Carrito carrito, MetodosPago pago, String orden_estado) {
        this.orden_id = orden_id;
        this.carrito = carrito;
        this.pago = pago;
        this.orden_estado = orden_estado;
    }

    public int getOrden_id() {
        return orden_id;
    }

    public void setOrden_id(int orden_id) {
        this.orden_id = orden_id;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public MetodosPago getPago() {
        return pago;
    }

    public void setPago(MetodosPago pago) {
        this.pago = pago;
    }

    public String getOrden_estado() {
        return orden_estado;
    }

    public void setOrden_estado(String orden_estado) {
        this.orden_estado = orden_estado;
    }

    @Override
    public String toString() {
        return "Orden{" + "orden_id=" + orden_id + ", carrito=" + carrito + ", pago=" + pago + ", orden_estado=" + orden_estado + '}';
    }
}