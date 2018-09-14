package test.condiciones;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dominio.regla.Regla;
import dominio.sensor.CondicionPorMenor;

public class testCondicionPorMenor {

	CondicionPorMenor menorA20;
	Regla mockRegla;

	@Before
	public void setUp() {

		mockRegla = Mockito.mock(Regla.class);
		menorA20 = new CondicionPorMenor(mockRegla, 20, "Temperatura");
	}

	@Test
	public void testCumpleCondicionPorMenor() {

		menorA20.setMedicionActual(14);
		assertEquals(true, menorA20.cumpleCondicion());
	}

	@Test
	public void testNoCumpleCondicionPorMenor() {

		menorA20.setMedicionActual(39);
		assertEquals(false, menorA20.cumpleCondicion());
	}
}
