package test.condiciones;

import dominio.regla.Regla;
import dominio.sensor.CondicionPorIgual;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class testCondicionPorIgual {

    CondicionPorIgual igualA30;
    Regla mockRegla;

    @Before
    public void setUp() {

        mockRegla = Mockito.mock(Regla.class);
        igualA30 = new CondicionPorIgual(mockRegla, 30, "Humedad");
    }

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
}
