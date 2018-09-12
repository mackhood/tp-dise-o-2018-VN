package test;

import dominio.entities.TransformadorNullException;
import dominio.entities.ZonaNullException;
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
	Ubicacion ubicacionCliente;
	Cliente clienteMock;
	Cliente clienteMock2;

	@Before
	public void setUp() {

		transmormadorMock1 = mock(Transformador.class);
		transmormadorMock2 = mock(Transformador.class);
		transmormadorMock3 = mock(Transformador.class);

		clienteMock = mock( Cliente.class);
		clienteMock2 = mock(Cliente.class);


		List<Transformador> listaDeTrasformadoresDeLaZona = new ArrayList<>();
		listaDeTrasformadoresDeLaZona.add(transmormadorMock1);
		listaDeTrasformadoresDeLaZona.add(transmormadorMock2);
		listaDeTrasformadoresDeLaZona.add(transmormadorMock3);

		when(transmormadorMock1.energiaConsumidaClientes()).thenReturn(100.0);
		when(transmormadorMock2.energiaConsumidaClientes()).thenReturn(200.0);
		when(transmormadorMock3.energiaConsumidaClientes()).thenReturn(200.0);

		ubicacionCliente = new Ubicacion(1,1);
		when(clienteMock.getPosicion()).thenReturn(ubicacionCliente);

		when(transmormadorMock1.calcularDistancia(clienteMock.getPosicion())).thenReturn(1.0);
		when(transmormadorMock2.calcularDistancia(clienteMock.getPosicion())).thenReturn(2.0);
		when(transmormadorMock3.calcularDistancia(clienteMock.getPosicion())).thenReturn(3.0);

		zonaGeografica1 = new ZonaGeografica("Zona1", listaDeTrasformadoresDeLaZona,new Ubicacion(1,1),1.0);

		when(clienteMock2.getPosicion()).thenReturn(new Ubicacion(10.0,10.0));
	}

	@Test
	public void testConsumoTotalDelazona() {
		assertEquals(500.0,zonaGeografica1.consumoTotal());
	}
	@Test
	public void testTransformadorCercanoAcliente() {
		assertEquals(transmormadorMock1,zonaGeografica1.conectarATransformadorCercano(clienteMock));
	}
	@Test
	public void testElclienteNoPerteneceZona() {

		//assertEquals(100,new Ubicacion(10.0,10.0).calcularDistancia(new Ubicacion(1,1)));

		assertFalse(zonaGeografica1.perteneceClienteAZona(clienteMock2));
	}
	@Test
	public void testCalcularDistanciaAcliente(){
		assertEquals(12.727922061357855,zonaGeografica1.distanciaAcliente(clienteMock2));
	}
	@Test(expected = TransformadorNullException.class)
	public void testLazonaNotieneTrasformador() {
		ZonaGeografica zonaGeografica2 = new ZonaGeografica("Zona1", new ArrayList<>(),new Ubicacion(1,1),10.0);
		zonaGeografica2.devolverTransformadorCercano(ubicacionCliente);
	}


}
