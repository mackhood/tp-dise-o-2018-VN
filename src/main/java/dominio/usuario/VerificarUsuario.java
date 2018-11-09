package dominio.usuario;

import dominio.manager.ClienteManager;

public class VerificarUsuario {

    public static boolean verificar(String usuario, String contrasenia)
    {
        if(usuario.isEmpty() || contrasenia.isEmpty())
        {
            return false;
        }

        Cliente cliente = ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario(usuario);

        if(cliente == null)
        {
            return false;
        }

        return cliente.getContrasenia().equals(contrasenia);


    }
}
