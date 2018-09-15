package test.database;

import dominio.cargarCliente.ClienteManager;
import dominio.usuario.Cliente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class testClienteDispositivo extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Before
	public void setup() {

	}

	@Test
	public void testRecuperarClienteConId1YCompararSiSuDispositivoInteligente1EsElAireAcondicionado() {
		Cliente unCliente = entityManager().find(Cliente.class, (new Long(1)));
		// System.out.println(unCliente.getDispositivosInteligentes().get(0).getNombre());
		// System.out.println(unCliente.getTodosLosDispositivos().get(1).getNombre());
		Assert.assertEquals("aireAcondicionado", unCliente.getDispositivosInteligentes().get(0).getNombre());
	}

	@Test
	public void testRecuperarClienteConId1YModificarNombreDispositivoInteligente1() {
		ClienteManager clienteManager = new ClienteManager();
		clienteManager.modificarNombreDeUnDispositivoDelCliente(new Long(1), "aireAcondicionado",
				"aireAcondicionadoModif");
	}

	@After
	public void end() {

	}
}
