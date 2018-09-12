package test.actuadores;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dominio.actuador.OrdenEncenderDI;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.EstadoApagado;

public class testOrdenEncenderDI {

	OrdenEncenderDI ordenEncenderDI;
	DispositivoInteligente unDI;

	@Before
	public void setUp() {

		List<DispositivoInteligente> listaDispositivosApagados = new ArrayList<>();
		ordenEncenderDI = new OrdenEncenderDI(listaDispositivosApagados);

		unDI = Mockito.spy(new DispositivoInteligente.DispositivoInteligenteBuilder("MockDI")
				.estadoDispositivo(new EstadoApagado()).build());

		listaDispositivosApagados.add(unDI);
	}

	@Test
	public void testDIApagadoSeEnciende() {
		ordenEncenderDI.ejecutar();
		assertTrue(unDI.estaEncendido());
	}

	@Test
	public void testDIApagadoSigueApagado() {
		ordenEncenderDI.ejecutarInversa();
		assertTrue(unDI.estaApagado());
	}
}
