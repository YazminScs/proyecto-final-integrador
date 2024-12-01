package DAOTest;

import Modelo.Usuario;
import java.util.List;

public interface IUsuarioDAO {

    boolean registrarUsuario(Usuario usuario);

    public List<Usuario> obtenerUsuarios();
    
    Usuario buscarUsuarioPorId(int usuario_id);
    
    boolean actualizarUsuario(Usuario usuario);
    
    boolean eliminarUsuario(int usuario_id);
    
    List<Usuario> obtenerUsuariosPorRol(String usuario_rol);
}
