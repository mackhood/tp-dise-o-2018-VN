package test;

import static junit.framework.TestCase.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dominio.actuador.*;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.DispositivoInteligente.DispositivoInteligenteBuilder;
import dominio.dispositivo.EstadoApagado;
import dominio.dispositivo.EstadoEncendido;

public class testActuadores {

	OrdenEncenderDI ordenEncenderDI;
	OrdenApagarDI ordenApagarDI;
	OrdenPonerModoAhorro ordenPonerModoAhorro;
	OrdenSubirIntensidad ordenSubirIntensidad;
	DispositivoInteligente unDI;
	DispositivoInteligente otroDI;

	@Before
	public void setUp() {

		List<DispositivoInteligente> listaDispositivosApagados = new ArrayList<>();
		List<DispositivoInteligente> listaDispositivosEncendidos = new ArrayList<>();
		ordenEncenderDI = new OrdenEncenderDI(listaDispositivosApagados);
		ordenApagarDI = new OrdenApagarDI(listaDispositivosEncendidos);
		ordenPonerModoAhorro = new OrdenPonerModoAhorro(listaDispositivosApagados);
		
		unDI = Mockito.spy(new DispositivoInteligente.DispositivoInteligenteBuilder("MockDI")
				.estadoDispositivo(new EstadoEncendido()).horaEncendido(LocalDateTime.now()).build());
		otroDI = Mockito.spy(new DispositivoInteligente.DispositivoInteligenteBuilder("OtroMockDI")
				.estadoDispositivo(new EstadoApagado()).build());
	
		listaDispositivosApagados.add(otroDI);
		listaDispositivosEncendidos.add(unDI);
	}

	@Test
	public void testDIApagadoSeEnciende() {
		ordenEncenderDI.ejecutar();
		assertEquals(true, otroDI.estaEncendido());
	}

	@Test
	public void testDIApagadoSePoneEnModoAhorro() {
		ordenPonerModoAhorro.ejecutar();
		assertEquals(true, otroDI.estaEnModoAhorro());
	}

	@Test
	public void testDIEncendidoSeApaga() {
		ordenApagarDI.ejecutar();
		assertEquals(true, unDI.estaApagado());
	}
}