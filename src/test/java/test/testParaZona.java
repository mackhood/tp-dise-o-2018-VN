package test;

import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.zonageografica.Ubicacion;
import dominio.zonageografica.ZonaGeografica;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testParaZona {

	Transformador transmormadorMock1;
	Transformador transmormadorMock2;
	Transformador transmormadorMock3;
	ZonaGeografica zonaGeografica1;

	@Before
	public void setUp() {

		transmormadorMock1 = mock(Transformador.class);
		transmormadorMock2 = mock(Transformador.class);
		transmormadorMock3 = mock(Transformador.class);

		List<Transformador> listaDeTrasformadoresDeLaZona = new ArrayList<>();
		listaDeTrasformadoresDeLaZona.add(transmormadorMock1);
		listaDeTrasformadoresDeLaZona.add(transmormadorMock2);
		listaDeTrasformadoresDeLaZona.add(transmormadorMock3);

		when(transmormadorMock1.energiaConsumidaClientes()).thenReturn(100.0);
		when(transmormadorMock2.energiaConsumidaClientes()).thenReturn(200.0);
		when(transmormadorMock3.energiaConsumidaClientes()).thenReturn(200.0);

		zonaGeografica1 = new ZonaGeografica("Zona1", listaDeTrasformadoresDeLaZona,new Ubicacion(1,1),10.0);

	}

	@Test
	public void testConsumoZona() {
		assertEquals(500.0,zonaGeografica1.consumoTotal());
	}
/*
	@Test
	public void testTransformadorMasCercano() {
		assertEquals(transformador2_Zona1, zonaGeografica1.devolverTransformadorCercano(clienteMock1.getPosicion()));
	}
*/
}
