package test;

import Clases.Cliente;
import Clases.repositories.RepositorioClientes;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

public class testRepositorioClientes {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testElRepositorioClientesEstaDevolviendoVacio () throws Exception{

			Cliente unCliente = null;
			RepositorioClientes repositorio =new RepositorioClientes();
			unCliente = repositorio.obtenerClientes().get(0);
			assertNotNull(unCliente);
	}



}
