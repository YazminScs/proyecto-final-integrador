package DAOTest;

import Modelo.Usuario;

public class UsuarioValidator {

    public static void validarUsuario(Usuario usuario) {
        System.out.println("Iniciando validaciones para el usuario: " + usuario.getUsername());

        if (usuario.getUsername() == null || usuario.getUsername().length() < 3) {
            throw new IllegalArgumentException("El nombre de usuario debe tener al menos 3 caracteres.");
        }

        if (usuario.getEmail() == null || !usuario.getEmail().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new IllegalArgumentException("El email no es válido.");
        }

        if (usuario.getPassword() == null || usuario.getPassword().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }

        if (usuario.getPhone() < 10000000 || usuario.getPhone() > 999999999) {
            throw new IllegalArgumentException("El número de teléfono debe ser válido.");
        }

        if (!usuario.getRol().equalsIgnoreCase("admin") && !usuario.getRol().equalsIgnoreCase("user")) {
            throw new IllegalArgumentException("El rol debe ser 'admin' o 'user'.");
        }

        System.out.println("Validaciones completadas exitosamente para el usuario: " + usuario.getUsername());
    }
}
