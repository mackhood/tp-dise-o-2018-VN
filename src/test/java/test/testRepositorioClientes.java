package test;

import Clases.Cliente;
import Clases.repositories.RepositorioClientes;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class testRepositorioClientes {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	//Reemplazar por json de Prueba
	@Test
	public void testObtenerCargoVariableDeLaCategoriaDelCliente() throws Exception{


			RepositorioClientes repositorio =new RepositorioClientes();
		    Cliente unCliente = repositorio.obtenerClientes().get(0);
			assertEquals("Ema",unCliente.nombreCompleto());
			assertEquals(18.76,unCliente.getCategoria().getCargoVariable());

	}



}
