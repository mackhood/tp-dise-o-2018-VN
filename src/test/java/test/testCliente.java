package test;

import dominio.categoria.AsignadorDeCategoria;
import dominio.categoria.Categoria;
import dominio.dispositivo.*;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.entities.NoTieneDispositivoException;
import dominio.entities.ProcessingDataFailedException;
import dominio.repositories.RepositorioCategoria;

import dominio.zonageografica.AsignadorDeZonaService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class testCliente {

	private DispositivoEstandar mockDEEncendido;
	private DispositivoEstandar mockDEApagado;
	private Cliente unClienteConDEyDI;
	private Cliente unClienteSinDEyConDI;
	private Dispositivo mockDispositivo;
	private DispositivoInteligente mockDIEncendido;
	private Conversor moduloAdaptador;

	private DispositivoEstandar mockDE;
	private DispositivoInteligente mockDI;

	private SistemaInteligente mockSI;

	Categoria categoriaMocktest1;
	Categoria categoriaMocktest2;
	List<Categoria> listaCategoriaMock;
	AsignadorDeCategoria asignadorMock;

	@Before
	public void setUp() {

		mockSI = Mockito.spy(new SistemaInteligente());
		mockDEEncendido = Mockito.mock(DispositivoEstandar.class);
		mockDEApagado = Mockito.mock(DispositivoEstandar.class);
		mockDispositivo = Mockito.mock(Dispositivo.class);
		moduloAdaptador = new Conversor();

		mockDE = Mockito.spy(new DispositivoEstandar.DispositivoEstandarBuilder("televisor")
				.equipoConcreto("Color de tubo fluorescente de 29 a 34").consumoEstimadoPorHora(30.0)
				.horasDeUso(2.0).build());
		
		mockDI = Mockito.spy(new DispositivoInteligente.DispositivoInteligenteBuilder("televisor").equipoConcreto("LED de 24")
						.consumoEstimadoPorHora(500.0).estadoDispositivo(new EstadoApagado())
						.horasDeUso(1.0).build());
		
		mockDIEncendido = Mockito.spy(new DispositivoInteligente.DispositivoInteligenteBuilder("aireAcondicionado")
				.equipoConcreto("De 2200 frigorias").consumoEstimadoPorHora(100.0).estadoDispositivo(new EstadoEncendido())
				.horasDeUso(19.0).build());

		List<DispositivoEstandar> listaDispositivosEstandar = new ArrayList<>();

		List<DispositivoEstandar> listaDispositivosParaOtroCliente = new ArrayList<>();

		List<DispositivoInteligente> listaDispInteligentes = new ArrayList<>();

		when(mockDEEncendido.getConsumoTotal()).thenReturn(25.5);
		when(mockDEApagado.getConsumoTotal()).thenReturn(25.5);

		unClienteConDEyDI = spy(new Cliente("Fernando", "Sierra", "fer22", new ID(TiposId.DNI, "200"),
				new Domicilio("bariloche", 3118, 1, 'a'), 250, listaDispositivosEstandar, listaDispInteligentes));
		unClienteSinDEyConDI = spy(new Cliente("Nicolas", "Sierra", "fer25", new ID(TiposId.DNI, "200"),
				new Domicilio("bariloche", 3118, 1, 'a'), 250, listaDispositivosParaOtroCliente,
				listaDispInteligentes));
		when(mockDispositivo.getConsumoTotal()).thenReturn(200.0);

		unClienteConDEyDI.agregarDispositivoInteligente(mockDIEncendido);
		unClienteConDEyDI.agregarDispositivoInteligente(mockDI);
		unClienteConDEyDI.agregarDispositivoEstandar(mockDE);

		// Siguientes lineas Utilizadas para test de actualizar categoria y gastos
		// aproximados.
		// asignadorMock = mock(AsignadorDeCategoria.class, Mockito.CALLS_REAL_METHODS);
		categoriaMocktest1 = mock(Categoria.class);
		categoriaMocktest2 = mock(Categoria.class);

		when(categoriaMocktest1.cumpleCondicion(unClienteConDEyDI)).thenReturn(true);
		when(categoriaMocktest2.cumpleCondicion(unClienteConDEyDI)).thenReturn(false);

		listaCategoriaMock = new ArrayList<>();

		listaCategoriaMock.add(categoriaMocktest1);
		listaCategoriaMock.add(categoriaMocktest2);

		asignadorMock = mock(AsignadorDeCategoria.class);

	}

	@Test
	public void testPuntosAcumuladosDespuesDeAgregar2DICliente() {
		assertEquals(30.0, unClienteConDEyDI.puntosAcumulados());
	}

	@Test(expected = NoTieneDispositivoException.class)
	public void testNoPuedeAgregarModulo() throws NoTieneDispositivoException {
		unClienteSinDEyConDI.agregarModuloAdaptador(moduloAdaptador, mockDE);
	}

	@Test
	public void testTieneDispositivo() {

		assertTrue(unClienteConDEyDI.tieneDE(mockDE));
	}

	@Test
	public void testTieneAlgunDispositivoEncendido() {

		assertTrue(unClienteConDEyDI.algunDispositivoEncendido());
	}

	@Test
	public void testCuantosDIEncendidos() {

		mockDI.encender();
		assertEquals(2, unClienteConDEyDI.cantidadDeDIEncendidos());
	}

	@Test
	public void testCuantosDIApagados() {

		assertEquals(1, unClienteConDEyDI.cantidadDeDIApagados());
	}

	@Test
	public void testAgregarDI() {

		DispositivoInteligente unDITest = Mockito.mock(DispositivoInteligente.class);
		unClienteConDEyDI.agregarDispositivoInteligente(unDITest);
		assertTrue(unClienteConDEyDI.getDispositivosInteligentes().contains(unDITest));
	}

	@Test
	public void testAgregarDE() {

		DispositivoEstandar unDETest = Mockito.mock(DispositivoEstandar.class);
		unClienteConDEyDI.agregarDispositivoEstandar(unDETest);
		assertTrue(unClienteConDEyDI.tieneDE(unDETest));
	}

	@Test
	public void testCuantosDispositivosTiene() {

		assertEquals(3, unClienteConDEyDI.cantidadDeDispositivos());
	}
	
	@Test
	public void testConsumoDICliente() {
		
		assertEquals(2400.0,unClienteConDEyDI.consumoDispositivosInteligentes());
	}
	
	@Test
	public void testSacarDE() {
		
		unClienteConDEyDI.sacarDispositivoEstandarLista(mockDE);
		assertFalse(unClienteConDEyDI.tieneDE(mockDE));
	}

	@Test(expected = NoTieneDispositivoException.class)
	public void testNoPuedeUsarDipositivoQueNoTiene() throws NoTieneDispositivoException {
		unClienteSinDEyConDI.usarDispositivo(mockDE, 5);
	}

	@Test
	public void testClienteUsarDispositivo() {
		
		unClienteConDEyDI.agregarDispositivoEstandar(mockDE);
		unClienteConDEyDI.usarDispositivo(mockDE, 3.0);
		assertEquals(5.0, mockDE.getHorasDeUso());
	}

	@Test
	public void testConsumoEnergeticoTotalDeUnCliente() {
		assertEquals(2460.0, unClienteConDEyDI.consumoEnergeticoTotal());
	}

	@Test
	public void testAhorroAutomatico() {
		unClienteConDEyDI.activarAhorroAutomatico();
		assertTrue(unClienteConDEyDI.estaEnModoAhorroAutomatico());
	}
	
	@Test
	public void testPuntosAcumuladorDespuesDeAgregarAdaptadorAUnDE() throws NoTieneDispositivoException {
		unClienteConDEyDI.agregarModuloAdaptador(moduloAdaptador, mockDE);
		assertEquals(40.0, unClienteConDEyDI.puntosAcumulados());
	}

	@Test
	public void testAlgunDIestaEncendidoClienteCon2DI() {

		assertEquals(true, mockSI.algunDIencendido(unClienteConDEyDI));
	}

	@Test
	public void testCantidadDIencendidosClienteCon2DI() {
		assertEquals(1, mockSI.cantidadDIencendidos(unClienteConDEyDI));
	}

	@Test
	public void testCantidadDIapagadosClienteCon2DI() {
		assertEquals(1, mockSI.cantidadDIapagados(unClienteConDEyDI));
	}

	@Test
	public void testCantidadDispositivosClienteCon2DIy1DE() {
		assertEquals(3, mockSI.cantidadDispositivos(unClienteConDEyDI));
	}

	@Test
	public void testConectarClienteATransformador() {
		AsignadorDeZonaService asignadorDeZonaService = mock(AsignadorDeZonaService.class);
		Transformador transformadorMock = mock(Transformador.class);
		when(asignadorDeZonaService.buscarTransformadorCercanoPara(unClienteConDEyDI)).thenReturn(transformadorMock);
		unClienteConDEyDI.conectarseATransformador(asignadorDeZonaService);
		assertEquals(transformadorMock, unClienteConDEyDI.getTransformador());
	}

	@Test
	public void testVerificarActualizacionDeCategoriaCliente() throws ProcessingDataFailedException {

		Categoria unaCategoriaMockSeteada = mock(Categoria.class);

		when(unaCategoriaMockSeteada.getNombre()).thenReturn("CategoriaR1");
		unClienteConDEyDI.setCategoria(unaCategoriaMockSeteada);

		assertEquals("CategoriaR1", unClienteConDEyDI.getNombreCategoria());
		when(asignadorMock.getCategoriasDelRepositorio()).thenReturn(listaCategoriaMock);
		when(categoriaMocktest1.getNombre()).thenReturn("CategoriaR2");
		when(asignadorMock.definirCategoriaPara(unClienteConDEyDI)).thenCallRealMethod();
		when(asignadorMock.categoriaCliente(unClienteConDEyDI, listaCategoriaMock)).thenCallRealMethod();

		assertEquals("CategoriaR2", asignadorMock.definirCategoriaPara(unClienteConDEyDI).getNombre());

	}
}
