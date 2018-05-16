package test;

import Clases.Dispositivo;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static junit.framework.TestCase.assertEquals;

public class testDispositivo {

    public Dispositivo unDispositivo;

    @Before
    public void setUp()  {
        unDispositivo = new Dispositivo("a1",300,false);
    }

    @Test
    public void testConsumoTotal(){
        assertEquals(300,unDispositivo.getConsumoTotal());
    }
    @Test
    public void  testCalcularIntervalo(){

        assertEquals(13,unDispositivo.calcularIntervalo(LocalTime.of(5, 05, 12, 10), LocalTime.of(8, 03, 02, 02)));
    }
}
