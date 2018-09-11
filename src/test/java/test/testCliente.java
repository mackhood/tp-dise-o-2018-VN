package test;

import dominio.categoria.AsignadorDeCategoria;
import dominio.categoria.Categoria;
import dominio.dispositivo.*;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import net.bytebuddy.asm.Advice.Thrown;
import dominio.entities.NoTieneDispositivoException;
import dominio.entities.ProcessingDataFailedException;
import dominio.repositories.RepositorioCategoria;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Mockito.*;

public class testCliente {

	private DispositivoEstandar unDispositivoEncendido;
	private DispositivoEstandar unDispositivoApagado;
	private Cliente unClienteConDEyDI;
	private Cliente unClienteSinDEyConDI;
	private Dispositivo otroDispositivo;
	private DispositivoInteligente unDIEncendido = new DispositivoInteligente();
	private Conversor moduloAdaptador;

	private DispositivoEstandar unDE;
	private DispositivoInteligente unDIApagado;
	// private final DispositivoInteligente unDIEncendido;
	private DispositivoInteligente unDETransformado;

	private SistemaInteligente unSI;

	Categoria categoriaMocktest1;
	Categoria categoriaMocktest2;
	List<Categoria> listaCategoriaMock;
	AsignadorDeCategoria asignadorMock;

	@Before
	public void setUp() {

		unSI = new SistemaInteligente();
		unDispositivoEncendido = mock(DispositivoEstandar.class);
		unDispositivoApagado = mock(DispositivoEstandar.class);
		otroDispositivo = mock(Dispositivo.class);
		moduloAdaptador = new Conversor();

		unDE = new DispositivoEstandar.DispositivoEstandarBuilder("a1").consumoEstimadoPorHora((double) 30).build();
		unDIApagado = new DispositivoInteligente.DispositivoInteligenteBuilder("da")
				.consumoEstimadoPorHora((double) 500).build();
		unDIEncendido = new DispositivoInteligente.DispositivoInteligenteBuilder("AireAcondicionado")
				.consumoEstimadoPorHora((double) 100).build();

		unDIEncendido.setHorasDeUso(1);
		unDIEncendido.encender();

		List<DispositivoEstandar> listaDispositivosEstandar = new ArrayList<>();

		listaDispositivosEstandar.add(unDE);
		List<DispositivoEstandar> listaDispositivosParaOtroCliente = new ArrayList<>();

		List<DispositivoInteligente> listaDispInteligentes = new ArrayList<>();

		when(unDispositivoEncendido.getConsumoTotal()).thenReturn(25.5);
		when(unDispositivoApagado.getConsumoTotal()).thenReturn(25.5);

		listaDispositivosEstandar.add(unDispositivoApagado);
		listaDispositivosEstandar.add(unDispositivoApagado);

		unClienteConDEyDI = spy(new Cliente("Fernando", "Sierra", "fer22", new ID(TiposId.DNI, "200"),
				new Domicilio("bariloche", 3118, 1, 'a'), 250, listaDispositivosEstandar, listaDispInteligentes));
		unClienteSinDEyConDI = spy(new Cliente("Nicolas", "Sierra", "fer25", new ID(TiposId.DNI, "200"),
				new Domicilio("bariloche", 3118, 1, 'a'), 250, listaDispositivosParaOtroCliente,
				listaDispInteligentes));
		when(otroDispositivo.getConsumoTotal()).thenReturn(200.0);

		unClienteConDEyDI.agregarDispositivoInteligente(unDIEncendido);
		unClienteConDEyDI.agregarDispositivoInteligente(unDIApagado);

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
		unClienteSinDEyConDI.agregarModuloAdaptador(moduloAdaptador, unDE);
	}

	@Test(expected = NoTieneDispositivoException.class)
	public void testNoPuedeUsarDipositivoQueNoTiene() throws NoTieneDispositivoException {
		unClienteSinDEyConDI.usarDispositivo(unDE, 5);
	}

	@Test
	public void testPuntosAcumuladorDespuesDeAgregarAdaptadorAUnDE() throws NoTieneDispositivoException {
		unClienteConDEyDI.agregarModuloAdaptador(moduloAdaptador, unDE);
		assertEquals(40.0, unClienteConDEyDI.puntosAcumulados());
	}

	@Test
	public void testAlgunDIestaEncendidoClienteCon2DI() {

		assertEquals(true, unSI.algunDIencendido(unClienteConDEyDI));
	}

	@Test
	public void testCantidadDIencendidosClienteCon2DI() {
		assertEquals(1, unSI.cantidadDIencendidos(unClienteConDEyDI));
	}

	@Test
	public void testCantidadDIapagadosClienteCon2DI() {
		assertEquals(1, unSI.cantidadDIapagados(unClienteConDEyDI));
	}

	@Test
	public void testCantidadDispositivosClienteCon2DIy1DE() {
		assertEquals(5, unSI.cantidadDispositivos(unClienteConDEyDI));
	}

	@Test
	public void testConsumoEnergeticoTotalDeUnCliente() {
		assertEquals(151.0, unClienteConDEyDI.consumoEnergeticoTotal());
	}

	@Test
	public void testVerificarActualizacionDeCategoriaCliente() throws ProcessingDataFailedException {

		Categoria unaCategoriaMockSeteada = mock(Categoria.class);

		when(unaCategoriaMockSeteada.getNombre()).thenReturn("CategoriaR1");
		unClienteConDEyDI.setCategoria(unaCategoriaMockSeteada);

		assertEquals("CategoriaR1", unClienteConDEyDI.nombreCategoria());

		when(asignadorMock.getCategoriasDelRepositorio()).thenReturn(listaCategoriaMock);
		when(categoriaMocktest1.getNombre()).thenReturn("CategoriaR2");
		when(asignadorMock.definirCategoriaPara(unClienteConDEyDI)).thenCallRealMethod();
		when(asignadorMock.categoriaCliente(unClienteConDEyDI, listaCategoriaMock)).thenCallRealMethod();

		assertEquals("CategoriaR2", asignadorMock.definirCategoriaPara(unClienteConDEyDI).getNombre());

	}
	@Test 
	public void asd() {
		
		RepositorioCategoria repo = RepositorioCategoria.getInstance();
		assertEquals("",repo.obtenerCategorias().get(0));
		
	}
	
	@Test
	public void testObtenerGastosAproximadosCliente() throws ProcessingDataFailedException {

		// Cargofijo=35.32
		// CargoVariable=0.644
		// ConsumoCliente=151.0
		// 35.32+0.644*151.0= 132.564

		when(asignadorMock.getCategoriasDelRepositorio()).thenReturn(listaCategoriaMock);
		when(categoriaMocktest1.getNombre()).thenReturn("CategoriaR2");
		when(asignadorMock.definirCategoriaPara(unClienteConDEyDI)).thenCallRealMethod();
		when(asignadorMock.categoriaCliente(unClienteConDEyDI, listaCategoriaMock)).thenCallRealMethod();

		when(categoriaMocktest1.getCargoVariable()).thenReturn(0.644);

		asignadorMock.definirCategoriaPara(unClienteConDEyDI);

		double gasto = 35.32 + categoriaMocktest1.getCargoVariable() * unClienteConDEyDI.consumoEnergeticoTotal();

		when(categoriaMocktest1.calcularCostosPara(unClienteConDEyDI)).thenReturn(gasto);

		unClienteConDEyDI.setCategoria(asignadorMock.definirCategoriaPara(unClienteConDEyDI));
		unClienteConDEyDI.obtenerGastosAproximados();

		verify(categoriaMocktest1).calcularCostosPara(unClienteConDEyDI);

		assertEquals(132.564, unClienteConDEyDI.obtenerGastosAproximados());

	}
}
