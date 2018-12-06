package test.condiciones;

import dominio.regla.Regla;
import dominio.sensor.CondicionPorMayor;
import dominio.sensor.Sensor;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class testCondicionPorMayor {

    CondicionPorMayor mayorA25;
    Regla mockRegla;
    Sensor mockSensor;

    @Before
    public void setUp() {

        mockRegla = Mockito.mock(Regla.class);
        mayorA25 = new CondicionPorMayor(25, "NivelDeRadiacion");
        mayorA25.asociarA(mockRegla);
        mockSensor = Mockito.mock(Sensor.class);
        when(mockSensor.getValorMedicion()).thenReturn(125.0);
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


    @Test
    public void testActualizar() {

        mayorA25.actualizar(mockSensor);
        TestCase.assertEquals(125.0, mockSensor.getValorMedicion());
    }


}
