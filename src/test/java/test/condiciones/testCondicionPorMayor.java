package test.condiciones;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dominio.regla.Regla;
import dominio.sensor.CondicionPorMayor;

public class testCondicionPorMayor {

	CondicionPorMayor mayorA25;
	Regla mockRegla;

	@Before
	public void setUp() {

		mockRegla = Mockito.mock(Regla.class);
		mayorA25 = new CondicionPorMayor(mockRegla, 25, "NivelDeRadiacion");
	}

	@Test
	public void testCumpleCondicionPorMayor() {

		mayorA25.setMedicionActual(41);
		assertEquals(true, mayorA25.cumpleCondicion());
	}

	@Test
	public void testNoCumpleCondicionPorMayor() {

		mayorA25.setMedicionActual(2);
		assertEquals(false, mayorA25.cumpleCondicion());
	}
}
