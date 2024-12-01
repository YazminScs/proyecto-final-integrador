package DAO;

import Modelo.Carrito;
import Modelo.Usuario;
import java.util.Date;
import java.util.List;

public interface ICarritoDAO {
    boolean registrarCarrito(Usuario usuario);
    
    boolean actualizarCarrito(double carrito_total, int carrito_id);
    
    int obtenerUltimoIdCarritoPorUsuario(Usuario usuario);
    
    Carrito obtenerCarritoPorId(int carrito_id);
    
    List<Carrito> obtenerCarritosPorUsuario(Usuario usuario);
    
    boolean eliminarCarritoPorId(int carrito_id);
}