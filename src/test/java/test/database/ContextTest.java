package test.database;

import dominio.cargarCliente.ClienteManager;
import dominio.usuario.DispositivosPersistirManager;
import org.junit.Ignore;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertNotNull;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

	private DispositivosPersistirManager dispositivosPersistirManager;
	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		withTransaction(() -> {
		});
	}

	@Test
	public void testCargarCliente() {
		ClienteManager clienteManager = new ClienteManager();
		clienteManager.persistirCliente();
		// clienteManager.persistirDispositivos();
		// entityManager().clear();
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
	}

	@Test
	public void cargarTablaDispositivos() {
		DispositivosPersistirManager.getInstance().persistirDispositivosDelRepositorio();
	}

}