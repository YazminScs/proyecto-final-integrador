package DAO;

import Modelo.Carrito;
import Modelo.MetodosPago;

public interface IOrdenDAO {
    boolean registrarOrden(Carrito carrito, MetodosPago pago, String orden_estado);
}
