package test.condiciones;

import dominio.regla.Regla;
import dominio.sensor.CondicionPorMenor;
import dominio.sensor.Sensor;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class testCondicionPorMenor {

    CondicionPorMenor menorA20;
    Regla mockRegla;
    Sensor mockSensor;

    @Before
    public void setUp() {

        mockRegla = Mockito.mock(Regla.class);
        menorA20 = new CondicionPorMenor(20, "Temperatura");
        menorA20.asociarA(mockRegla);
        mockSensor = Mockito.mock(Sensor.class);
        when(mockSensor.getValorMedicion()).thenReturn(125.0);
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

    @Test
    public void testActualizar() {

        menorA20.actualizar(mockSensor);
        TestCase.assertEquals(125.0, mockSensor.getValorMedicion());
    }


}
