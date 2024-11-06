package DAO;

import Modelo.Usuario;
import java.util.List;

public interface IUsuarioDAO {
     List<Usuario> obtenerUsuarios();
     
     boolean autenticar(String usuario_nom, String usuario_pass);
     
     boolean verificarPass(String usuario_pass, String pass_enc);
     
     boolean registrarUsuario(String usuario_nom, String usuario_email, String usuario_pass, String usuario_dir, int usuario_tel, String usuario_rol);
     
     boolean existeNombreUsuario(String usuario_nom);
     
     boolean existeEmailUsuario(String usuario_email);
     
     String obtenerRol(String usuario_nom);
}