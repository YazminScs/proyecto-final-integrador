package DAO;

import Modelo.Carrito;
import Modelo.Usuario;
import java.util.Date;

public interface ICarritoDAO {
    boolean registrarCarrito(Usuario usuario);
    
    boolean actualizarCarrito(double carrito_total, int carrito_id);
    
    int obtenerUltimoIdCarritoPorUsuario(Usuario usuario);
    
    Carrito obtenerCarritoPorId(int carrito_id);
    
    boolean eliminarCarritoPorId(int carrito_id);
}