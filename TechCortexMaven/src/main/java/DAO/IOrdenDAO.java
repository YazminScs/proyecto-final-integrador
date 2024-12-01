package DAO;

import Modelo.Carrito;
import Modelo.MetodosPago;
import Modelo.Orden;

public interface IOrdenDAO {
    boolean registrarOrden(Carrito carrito, MetodosPago pago, String orden_estado);
    
    Orden obtenerOrdenPorCarrito(Carrito carrito);
}