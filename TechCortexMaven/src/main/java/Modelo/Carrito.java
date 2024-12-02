package Modelo;

import java.util.Date;

public class Carrito {
    private int carrito_id;
    private Date carrito_fecha;
    private Usuario usuario;
    private double carrito_total;

    public Carrito() {
    }

    public Carrito(int carrito_id, Date carrito_fecha, Usuario usuario, double carrito_total) {
        this.carrito_id = carrito_id;
        this.carrito_fecha = carrito_fecha;
        this.usuario = usuario;
        this.carrito_total = carrito_total;
    }

    public int getCarrito_id() {
        return carrito_id;
    }

    public void setCarrito_id(int carrito_id) {
        this.carrito_id = carrito_id;
    }

    public Date getCarrito_fecha() {
        return carrito_fecha;
    }

    public void setCarrito_fecha(Date carrito_fecha) {
        this.carrito_fecha = carrito_fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getCarrito_total() {
        return carrito_total;
    }

    public void setCarrito_total(double carrito_total) {
        this.carrito_total = carrito_total;
    }

    @Override
    public String toString() {
        return "Carrito{" + "carrito_id=" + carrito_id + ", carrito_fecha=" + carrito_fecha + ", usuario=" + usuario + ", carrito_total=" + carrito_total + '}';
    }
}