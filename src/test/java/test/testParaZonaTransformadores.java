package test;

import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.zonageografica.Ubicacion;
import dominio.zonageografica.ZonaGeografica;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testParaZonaTransformadores {

	private Cliente clienteMock1;
	private Cliente clienteMock2;
	private Cliente clienteMock3;

	private Transformador transformador1_Zona1;
	private Transformador transformador2_Zona1;

	private Transformador transformador_OtraZona;

	private ZonaGeografica zonaGeografica1;
	private ZonaGeografica zonaGeografica2;

	private Ubicacion ubicacionMock;
	private Ubicacion ubicacionZona1;
	private Ubicacion ubicacionZona2;

	private List<Transformador> listaTransformadorZona1 = new ArrayList<>();
	List<Transformador> listaTransformadorOtraZona = new ArrayList<>();

	@Before
	public void setUp() {

		clienteMock1 = mock(Cliente.class);
		clienteMock2 = mock(Cliente.class);
		clienteMock3 = mock(Cliente.class);

		transformador_OtraZona = mock(Transformador.class);


		listaTransformadorOtraZona.add(transformador_OtraZona);

		List<Cliente> listaClientesTransformador1 = new ArrayList<>();
		List<Cliente> listaClientesTransformador2 = new ArrayList<>();

		listaClientesTransformador1.add(clienteMock1);
		listaClientesTransformador1.add(clienteMock2);

		listaClientesTransformador2.add(clienteMock3);

		transformador1_Zona1 = new Transformador(listaClientesTransformador1, new Ubicacion(20.0, 20.0), 40);
		transformador2_Zona1 = new Transformador(listaClientesTransformador2, new Ubicacion(10.0, 10.0), 5);

		when(clienteMock1.consumoEnergeticoTotal()).thenReturn(350.0);
		when(clienteMock2.consumoEnergeticoTotal()).thenReturn(400.0);
		when(clienteMock3.consumoEnergeticoTotal()).thenReturn(200.0);

		when(transformador_OtraZona.energiaConsumidaClientes()).thenReturn(600.0);

		listaTransformadorZona1.add(transformador1_Zona1);
		listaTransformadorZona1.add(transformador2_Zona1);

		zonaGeografica1 = new ZonaGeografica("Zona1", listaTransformadorZona1,ubicacionZona1,10.0);
		zonaGeografica2 = new ZonaGeografica("Zona2", listaTransformadorOtraZona,ubicacionZona2,20.0);

		ubicacionMock = mock(Ubicacion.class);

		when(ubicacionMock.getPosicionX()).thenReturn(3.0);
		when(ubicacionMock.getPosicionY()).thenReturn(4.0);

		when(clienteMock1.getPosicion()).thenReturn(ubicacionMock);

		ubicacionZona1 = mock(Ubicacion.class);

		when(ubicacionZona1.getPosicionX()).thenReturn(3.0);
		when(ubicacionZona1.getPosicionY()).thenReturn(4.0);

		ubicacionZona2 = mock(Ubicacion.class);

		when(ubicacionZona2.getPosicionX()).thenReturn(3.0);
		when(ubicacionZona2.getPosicionY()).thenReturn(4.0);

	}

	@Test
	// tengo un problema con el equals (double,double) por eso cree este metodo
	public void testConsumoTransformadorMayorA700() {
		// assertEquals(750.0,transformador1_Zona1.energiaConsumidaClientes());
		assertTrue(transformador1_Zona1.energiaMayorA700());
	}

	@Test
	public void testConsumoTransformadorMenorA700() {
		// assertEquals(750.0,transformador1_Zona1.energiaConsumidaClientes());
		assertFalse(transformador2_Zona1.energiaMayorA700());
	}

	@Test
	public void testConsumoZona() {
		// assertEquals(transformador1_Zona1,zonaGeografica1.getTransformadores().get(0));

		// Consumo transformador 1 = 750 + conssumo transformador 2 = 200 -> 950
		assertTrue(zonaGeografica1.energiaMayorA900());

	}

	@Test
	public void testTransformadorMasCercano() {

		assertEquals(transformador2_Zona1, zonaGeografica1.devolverTransformadorCercano(clienteMock1.getPosicion()));

	}

}
