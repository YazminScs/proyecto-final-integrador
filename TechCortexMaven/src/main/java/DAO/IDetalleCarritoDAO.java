package DAO;

import Modelo.Carrito;
import Modelo.DetalleCarrito;
import Modelo.Producto;
import java.util.List;

public interface IDetalleCarritoDAO {
    boolean registrarDetalleCarrito(int detalle_cant, double detalle_price, Carrito carrito, Producto producto);
    
    boolean validarProductoEnCarrito(Carrito carrito, Producto producto);
    
    List<DetalleCarrito> listarDetalleCarritoPorId(int carrito_id);
    
    boolean actualizarCantidad(int detalle_cant, Producto producto, Carrito carrito);
    
    boolean eliminarProducto(Carrito carrito, Producto producto);
    
    double obtenerTotalPorId(int carrito_id);
}
