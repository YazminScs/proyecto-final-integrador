package DAO;

import Modelo.MetodosPago;

public interface IMetodoPagoDAO {
    MetodosPago obtenerPagoPorId(int pago_id);
}
