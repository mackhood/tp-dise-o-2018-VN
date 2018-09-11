package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import dominio.regla.Regla;
import dominio.sensor.CondicionPorIgual;
import dominio.sensor.CondicionPorMayor;
import dominio.sensor.CondicionPorMenor;
import org.mockito.Mockito;

public class testCondiciones {

CondicionPorMenor menorA20;
CondicionPorMayor mayorA25;
CondicionPorIgual igualA30;
Regla mockRegla;

	@Before
	public void setUp() {
		
		mockRegla = Mockito.mock(Regla.class);
		menorA20 = new CondicionPorMenor(mockRegla,20,"Temperatura");
		mayorA25 = new CondicionPorMayor(mockRegla,25,"NivelDeRadiacion");
		igualA30 = new CondicionPorIgual(mockRegla,30,"Humedad");
	}
	
	@Test
	public void testCumpleCondicionPorMenor() {
		
		menorA20.setMedicionActual(14);
		assertEquals(true,menorA20.cumpleCondicion());
	}
	
	@Test
	public void testNoCumpleCondicionPorMenor() {
		
		menorA20.setMedicionActual(39);
		assertEquals(false,menorA20.cumpleCondicion());
	}
	
	@Test
	public void testCumpleCondicionPorMayor() {
		
		mayorA25.setMedicionActual(41);
		assertEquals(true,mayorA25.cumpleCondicion());
	}
	
	@Test
	public void testNoCumpleCondicionPorMayor() {
		
		mayorA25.setMedicionActual(2);
		assertEquals(false,mayorA25.cumpleCondicion());
	}
	
	@Test 
	public void testCumpleCondicionPorIgual() {
		
		igualA30.setMedicionActual(30);
		assertEquals(true,igualA30.cumpleCondicion());
	}
	
	@Test 
	public void testNoCumpleCondicionPorIgual() {
		
		igualA30.setMedicionActual(9);
		assertEquals(false,igualA30.cumpleCondicion());
	}
}
