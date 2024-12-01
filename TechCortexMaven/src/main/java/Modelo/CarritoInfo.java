package Modelo;

import java.util.List;

public class CarritoInfo {
    private Carrito carrito;
    private List<DetalleCarrito> detalles;
    private Orden orden;

    public CarritoInfo() {
    }

    public CarritoInfo(Carrito carrito, List<DetalleCarrito> detalles, Orden orden) {
        this.carrito = carrito;
        this.detalles = detalles;
        this.orden = orden;
    }

 
    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public List<DetalleCarrito> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCarrito> detalles) {
        this.detalles = detalles;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }
    
    
}
