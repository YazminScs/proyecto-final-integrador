package Modelo;

public class DetalleCarrito {
    private int detalle_id;
    private int detalle_cant;
    private double detalle_price;
    private Carrito carrito;
    private Producto producto;

    public DetalleCarrito() {
    }

    public DetalleCarrito(int detalle_id, int detalle_cant, double detalle_price, Carrito carrito, Producto producto) {
        this.detalle_id = detalle_id;
        this.detalle_cant = detalle_cant;
        this.detalle_price = detalle_price;
        this.carrito = carrito;
        this.producto = producto;
    }

    public int getDetalle_id() {
        return detalle_id;
    }

    public void setDetalle_id(int detalle_id) {
        this.detalle_id = detalle_id;
    }

    public int getDetalle_cant() {
        return detalle_cant;
    }

    public void setDetalle_cant(int detalle_cant) {
        this.detalle_cant = detalle_cant;
    }

    public double getDetalle_price() {
        return detalle_price;
    }

    public void setDetalle_price(double detalle_price) {
        this.detalle_price = detalle_price;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "DetalleCarrito{" + "detalle_id=" + detalle_id + ", detalle_cant=" + detalle_cant + ", detalle_price=" + detalle_price + ", carrito=" + carrito + ", producto=" + producto + '}';
    }
}