package test;

import Clases.Dispositivo;
import Clases.DispositivoEstandar;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static junit.framework.TestCase.assertEquals;

public class testDispositivo {

    public Dispositivo unDispositivo;

    @Before
    public void setUp()  {
        unDispositivo = new DispositivoEstandar("a1",300);
    }

    @Test
    public void testConsumoTotal(){
        assertEquals(0.0,unDispositivo.getConsumoTotal());
    }
    @Test
    public void testConsultaDeEstadoDeUnDispositivoEstandar()
    {
        assertEquals("Como es estandar => Nulo",null, unDispositivo.estadoDispositivo());
    }
    @Test
    public void testConsultaDeConsumoTotalDeUnDispositivoEstandar()
    {
        assertEquals("Como es estandar => 0",0.0, unDispositivo.getConsumoTotal());
    }
    @Test
    public  void testConsultaConsumoDeUnDispositivoEstandar()
    {
        assertEquals("Como es estandar => tiene una estimacion",300.0, unDispositivo.estimacionConsumo());
    }

}
