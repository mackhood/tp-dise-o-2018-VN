package dominio.usuario;

import dominio.manager.AdministradorManager;

public class VerificarAdmin {


    public static boolean verificar(String usuario, String contrasenia) {
        if (usuario.isEmpty() || contrasenia.isEmpty()) {
            return false;
        }
        Administrador administrador = AdministradorManager.getInstance().getAdministradorDeLaBDPorUsuario(usuario);
        if (administrador == null) {

            return false;
        }

        return administrador.getContrasenia().equals(contrasenia);


    }
}







