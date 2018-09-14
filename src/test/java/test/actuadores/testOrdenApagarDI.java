package test.actuadores;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dominio.actuador.OrdenApagarDI;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.EstadoEncendido;

public class testOrdenApagarDI {

	OrdenApagarDI ordenApagarDI;
	DispositivoInteligente unDI;

	@Before
	public void setUp() {
		List<DispositivoInteligente> listaDispositivosEncendidos = new ArrayList<>();
		ordenApagarDI = new OrdenApagarDI(listaDispositivosEncendidos);

		unDI = Mockito.spy(new DispositivoInteligente.DispositivoInteligenteBuilder("MockDI")
				.estadoDispositivo(new EstadoEncendido()).horaEncendido(LocalDateTime.now()).build());

		listaDispositivosEncendidos.add(unDI);
	}

	@Test
	public void testDIEncendidoSeApaga() {
		ordenApagarDI.ejecutar();
		assertTrue(unDI.estaApagado());
	}

	@Test
	public void testDIEncendidoSigueEncendido() {
		ordenApagarDI.ejecutarInversa();
		assertTrue(unDI.estaEncendido());
	}
}
