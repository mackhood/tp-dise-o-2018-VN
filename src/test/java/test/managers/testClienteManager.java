package test.managers;

import dominio.categoria.Categoria;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import persistence.ClienteManager;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class testClienteManager {
	private ClienteManager instance;
	private ClienteManager mockClienteManager;
	private Cliente mockCliente;
	private Domicilio mockDomicilio;
	private ID mockId;
	private DispositivoEstandar mockDispositivoEstandar;

	@Before
	public void setUp() {

		instance = ClienteManager.getInstance();

		mockDomicilio = Mockito.spy(new Domicilio("Alem", 204, 9, 'B'));
		mockId = Mockito.spy(new ID(TiposId.DNI, "37110945"));
		mockClienteManager = Mockito.spy(ClienteManager.getInstance());

		mockCliente = Mockito.spy(
				new Cliente("Ariel", "Galvan", "galvanariel", "password", mockId, mockDomicilio, 42211000, null, null));

		mockDispositivoEstandar = Mockito.spy(
				new DispositivoEstandar.DispositivoEstandarBuilder("a1").consumoEstimadoPorHora((double) 300).build());

		List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();

		List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();
	}

	@Test
	public void testGetInstance() {

		assertEquals(instance.getClass(), ClienteManager.class);
	}

	@Test
	public void esClienteVerdadero() {

		assertEquals(true, instance.esCliente("galvanariel"));
	}

	@Test
	public void esClienteFalso() {

		assertEquals(false, instance.esCliente("xxxxxx"));
	}

	@Test
	public void testBuscarClienteDeLaBDPorUsuario() {

		assertEquals(Cliente.class, instance.buscarClienteDeLaBDPorUsuario("galvanariel").getClass());
	}

	@Test
	public void testBuscarIDDelClienteDeLaBDPorUsuario() {
		Long num = new Long(41);
		Assert.assertEquals(num, ClienteManager.getInstance().getIdDelClientePorUsuario("galvanariel"));
	}

	/*
	 * @Test public void testGetIdCliente () { ClienteManager instance =
	 * ClienteManager.getInstance();
	 * 
	 * 
	 * assertEquals(mockCliente.getId().getClass(),instance.
	 * getIdDelClientePorUsuario(mockCliente.getUsuario()).getClass());
	 * 
	 * 
	 * }
	 */

	@Test
	public void testOtenerPrimerosNClientes() {

		assertEquals(2, instance.obtenerPrimerosNClientes(6).size());
	}

	@Test
	public void getClientesDeLaBD() {

		assertEquals(2, instance.getClientesDeLaBD().size());
	}

	@Test
	public void testConsumoHogar() {
		// Tiene 23 horas de consumo por dispositivo. Uno tiene un consumo de 0.7 kwh y
		// el otro 1.11 kwh
		assertEquals(41.63,
				instance.consumoHogar("fernandosierra9", LocalDateTime.of(2018, 8, 01, 00, 00), LocalDateTime.now()),
				0.1);
	}

	@Test
	public void testIntervalosDeUso() {
		// Tiene 2 dispositivos con 3 intervalos cada uno
		assertEquals(6, instance.getIntervalosDeUso(instance.getIdDelClientePorUsuario("fernandosierra9")).size());
	}

	@Test
	public void testDispositivosConsumo() {
		/*
		 * Tiene 2 dispositivos con 3 intervalos cada uno. El metodo fue creado para
		 * obtener el dispositivo asociado a cada intervalo, por lo que deberia devolver
		 * un dispositivo por cada intervalo. Cada dispositivo tiene 3 intervalos, por
		 * lo que el resultado correcto seria 6, trayendo 3 veces cada uno de los dos
		 * dispositivos
		 */

		assertEquals(6, instance.getDispositivoConsumo(instance.getIdDelClientePorUsuario("fernandosierra9")).size());
	}

	@Test
	public void testValorConsumos() {
		/*
		 * Este metodo trae una lista con el valor de consumo de cada intervalo. Para el
		 * test, vamos a sumar cada valor y verificar que coincida con el consumo total
		 * del hogar
		 */

		assertEquals(
				instance.consumoHogar("fernandosierra9", LocalDateTime.of(100, 01, 01, 00, 00), LocalDateTime.now()),
				instance.valorConsumosDeCliente(instance.getIdDelClientePorUsuario("fernandosierra9")).stream()
						.mapToDouble(Double::doubleValue).sum(),
				0.1);
	}

	@Test
	public void testFiltrarClientes() {

		assertEquals(mockCliente.getApellido(),
				instance.filtradoClientes("Ariel", "Galvan", mockDomicilio.calle).get(0).getApellido());
	}

	@Test
	public void testFiltrarClientesPorApellido() {

		ClienteManager instance = ClienteManager.getInstance();
		List<Cliente> clientes = new ArrayList<>();
		clientes = mockClienteManager.filtrarClientesPorApellido("Galvan");
		assertEquals(clientes, instance.filtrarClientesPorApellido("Galvan"));
	}

	@Test
	public void testTieneDispositivos() {
		assertTrue(instance.tieneDispositivos(instance.getIdDelClientePorUsuario("fernandosierra9")));
	}

	@Test
	public void testUltimoIntervalo() {
		/*
		 * El ultimo intervalo del usuario "fernandosierra9" data del 2018-10-13T01:45
		 * (fecha de finalizacion). En el test, vamos a verificar que usando el metodo,
		 * se obtenga este intervalo comparando su fecha de finalizacion 
		 * NOTA: Le sumo 3 horas para compensar la diferencia de timezone de MYSQL/LocalDateTime
		 */

		assertEquals(LocalDateTime.of(2018, 10, 13, 01, 45, 00),
				instance.ultimoIntervalo(instance.getIdDelClientePorUsuario("fernandosierra9")).getFin().plusHours(3));
	}
	
	@Test
	public void testDispositivosExcedidos()
	{
		assertEquals(1,instance.getDispositivosExcedidos("galvanariel").size());
	}
}