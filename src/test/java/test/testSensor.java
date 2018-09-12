package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dominio.actuador.OrdenSubirIntensidad;
import dominio.regla.Regla;
import dominio.sensor.Condicion;
import dominio.sensor.CondicionPorIgual;
import dominio.sensor.Medicion;
import dominio.sensor.Sensor;

public class testSensor {

	Sensor unSensor;
	Regla mockRegla;
	OrdenSubirIntensidad mockActuador;
	CondicionPorIgual mockCondicion;
	Medicion mockMedicion;
	
	@Before
	public void setUp() {
		
		List <Condicion> listaCondiciones = new ArrayList<>();
		mockMedicion = Mockito.spy(new Medicion(20,"Temperatura"));
		mockActuador = Mockito.mock(OrdenSubirIntensidad.class);
		mockRegla = Mockito.spy(new Regla(mockActuador,listaCondiciones));
		unSensor = new Sensor(mockRegla);
		mockCondicion = Mockito.spy(new CondicionPorIgual(mockRegla,1,"Movimiento"));
		mockRegla.agregarCondicion(mockCondicion);
	}
	
	@Test
	public void testTomarMedicion() {
		
		unSensor.recibirMedicion(mockMedicion);
		assertEquals(20,unSensor.getValorMedicion(),1);
	}
}