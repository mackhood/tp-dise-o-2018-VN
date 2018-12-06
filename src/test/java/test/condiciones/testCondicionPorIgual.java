package test.condiciones;

import dominio.regla.Regla;
import dominio.sensor.CondicionPorIgual;
import dominio.sensor.Sensor;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class testCondicionPorIgual {

    CondicionPorIgual igualA30;
    Regla mockRegla;
    Sensor mockSensor;
    @Before
    public void setUp() {

        mockRegla = Mockito.mock(Regla.class);
        igualA30 = new CondicionPorIgual(30, "Humedad");
        igualA30.asociarA(mockRegla);
        mockSensor =Mockito.mock(Sensor.class);
        when(mockSensor.getValorMedicion()).thenReturn(125.0);    }


    @Test
    public void testCumpleCondicionPorIgual() {

        igualA30.setMedicionActual(30);
        assertEquals(true, igualA30.cumpleCondicion());
    }

    @Test
    public void testNoCumpleCondicionPorIgual() {

        igualA30.setMedicionActual(9);
        assertEquals(false, igualA30.cumpleCondicion());
    }


    @Test
    public void testActualizar () {

        igualA30.actualizar(mockSensor);
        TestCase.assertEquals(125.0, mockSensor.getValorMedicion());
    }
}
