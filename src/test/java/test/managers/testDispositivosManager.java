package test.managers;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import persistence.ClienteManager;
import persistence.DispositivosManager;
import persistence.TipoDispositivoManager;
import persistence.TransformadorManager;

import static junit.framework.TestCase.assertEquals;

public class testDispositivosManager {

	private DispositivosManager instance;

	@Before
	public void setUp() {
		instance = DispositivosManager.getInstance();
	}

	@Test
	public void testGetInstance() {

		assertEquals(instance.getClass(), DispositivosManager.class);
	}

	@Test
	public void testTipoDispositivoAireAcondicionado() {

		assertEquals("Aire Acondicionado", instance.getTipoPorNombre("Aire Acondicionado").getNombre());
	}

	@Test
	public void testGetDispositivoInteligenteDeLaBDPorID() {

		assertEquals("Aire Acondicionado", instance.getDispositivoInteligenteDeLaBDPorID(new Long(18)).getNombre());
	}

	@Test
	public void testGetDispositivoEstandarDeLaDPorEquipoConcreto() {

		assertEquals("Televisor",
				instance.getDispositivoEstandarDeLaDPorEquipoConcreto("Color de tubo fluorescente de 21").getNombre());
	}

	@Test
	public void testGetDispositivosInteligentesDeLaBD() {
		assertEquals(21, instance.getDispositivosInteligentesDeLaBD().size());
	}

	@Test
	public void testGetDispositivoPorDetalleEquipo() {
		assertEquals("Televisor", instance.getDispositivoPorDetalleEquipo("LED de 24").getNombre());
	}

	@Test
	public void testGetDispositivoEstandarDeLaBD() {
		assertEquals("Lavarropas", instance.getDispositivoEstandarDeLaBD(new Long(14)).getNombre());
	}

	@Test
	public void testGetDispositivosDeCliente() {
		assertEquals(2,
				instance.getDispositivosDeCliente(ClienteManager.getInstance().getIdDelClientePorUsuario("galvanariel"))
						.size());
	}

	@Test
	public void testTieneIntervalos() {
		assertTrue(instance.tieneIntervalos(new Long(34)));
	}

	@Test
	public void testCantidadTiposDispositivos() {
		assertEquals(9, instance.getTipos().size());
	}

	@Test
	public void testConsumoUltimoPeriodo() {
		// El ultimo intervalo registrado para el dispositivo con id 47 es de 9 horas *
		// 0.7 kw/h
		assertEquals(6.3, instance.getConsumoUltimoPeriodo((long) 47), 0.1);
	}

	@Test
	public void testDispositivoUltimoConsumo() {
		// El metodo devuelve el ultimo dispositivo utilizado para un cliente
		assertEquals("Tester1",
				instance.dispUltimoConsumo(ClienteManager.getInstance().getIdDelClientePorUsuario("fernandosierra9"))
						.getNombre());
	}

	@Test
	public void testTipoDispositivoPorID() {
		assertEquals("Aire Acondicionado",
				TipoDispositivoManager.getInstance().getTipoDispositivoDeLaBDPorID((long) 1).getNombre());
	}
	
	@Test
	public void testEstaEncendido()
	{
		assertTrue(instance.estaEncendido(instance.getDispositivoInteligenteDeLaBDPorID((long) 38).getId()));
	}
}
