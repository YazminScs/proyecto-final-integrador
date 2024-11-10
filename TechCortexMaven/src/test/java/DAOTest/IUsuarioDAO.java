package DAOTest;

import Modelo.Usuario;
import java.util.List;

public interface IUsuarioDAO {

    boolean registrarUsuario(String usuario_nom, String usuario_email, String usuario_pass, String usuario_dir, int usuario_tel, String usuario_rol);

    public List<Usuario> obtenerUsuarios();
    
}
