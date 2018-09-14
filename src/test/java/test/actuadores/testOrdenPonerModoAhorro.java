package test.actuadores;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dominio.actuador.OrdenPonerModoAhorro;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.EstadoEncendido;

public class testOrdenPonerModoAhorro {

		OrdenPonerModoAhorro ordenPonerModoAhorro;
		DispositivoInteligente unDI;

		@Before
		public void setUp() {

			List<DispositivoInteligente> listaDispositivosApagados = new ArrayList<>();

			ordenPonerModoAhorro = new OrdenPonerModoAhorro(listaDispositivosApagados);
			
			
			unDI = Mockito.spy(new DispositivoInteligente.DispositivoInteligenteBuilder("MockDI")
					.estadoDispositivo(new EstadoEncendido()).build());
		
			listaDispositivosApagados.add(unDI);
		}

		@Test
		public void testDIEncendidoSePoneEnModoAhorro() {
			ordenPonerModoAhorro.ejecutar();
			assertTrue(unDI.estaEnModoAhorro());
		}
		
		@Test
		public void testDIEncendidoSigueEncendido() {
			ordenPonerModoAhorro.ejecutarInversa();
			assertTrue(unDI.estaEncendido());
		}
	}