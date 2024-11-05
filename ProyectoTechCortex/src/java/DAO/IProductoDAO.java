package DAO;

import Modelo.Producto;
import java.util.List;

public interface IProductoDAO {
    List<Producto> listarProductos();
    List<Producto> listarTeclados();
    List<Producto> listarMonitores();
    boolean actualizarProducto(Producto p);
}