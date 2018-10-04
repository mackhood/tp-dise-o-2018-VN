package test.condiciones;

import dominio.regla.Regla;
import dominio.sensor.CondicionPorMenor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

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
