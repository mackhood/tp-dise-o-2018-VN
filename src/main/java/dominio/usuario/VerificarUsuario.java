package dominio.usuario;

import dominio.manager.AdministradorManager;
import dominio.manager.ClienteManager;

public class VerificarUsuario {

	public static boolean verificar(String usuario, String contrasenia) {
		
		if (usuario.isEmpty() || contrasenia.isEmpty()) {
			return false;
		}

		if (ClienteManager.getInstance().esCliente(usuario)) {
			
			Cliente cliente = ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario(usuario);
			return cliente.getContrasenia().equals(contrasenia);
		}
		
		else if (AdministradorManager.getInstance().esAdministrador(usuario))
		{
			Administrador admin = AdministradorManager.getInstance().getAdministradorDeLaBDPorUsuario(usuario);
			return admin.getContrasenia().equals(contrasenia);
		}
		
		else return false;
	}
}
