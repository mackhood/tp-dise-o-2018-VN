package test.managers;

import dominio.categoria.Categoria;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import persistence.AdministradorManager;
import persistence.ClienteManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class testAdministardorManager {

	private AdministradorManager instance;
	private AdministradorManager mockAdminManager;
	private Administrador mockAdministrador;
	private Domicilio mockDomicilio;
	private ID mockId;
	private DispositivoEstandar mockDispositivoEstandar;

	@Before
	public void setUp() {

		mockAdministrador = Mockito
				.spy(new Administrador("German", "Jugo", LocalDate.of(2016, 5, 18), "gerjor", "1234"));

		mockDispositivoEstandar = Mockito.spy(
				new DispositivoEstandar.DispositivoEstandarBuilder("a1").consumoEstimadoPorHora((double) 300).build());

		List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();

		List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();

	}

	@Test
	public void testGetInstance() {

		AdministradorManager instance = AdministradorManager.getInstance();

		assertEquals(instance.getClass(), AdministradorManager.class);

	}

	@Test
	public void testEsAdminVerdadero() {

		AdministradorManager instance = AdministradorManager.getInstance();

		assertEquals(true, instance.esAdministrador("gerjor"));

	}

	@Test
	public void testEsAdministrador() {
		AdministradorManager instance = AdministradorManager.getInstance();

		assertEquals(false, instance.esAdministrador("xxxxxx"));

	}

	@Test
	public void testGetAdministradorDeLaBDPorUsuario() {

		AdministradorManager instance = AdministradorManager.getInstance();

		assertEquals(Administrador.class, instance.getAdministradorDeLaBDPorUsuario("gerjor").getClass());

	}

}
