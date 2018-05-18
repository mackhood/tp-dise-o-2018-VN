package test;

import Clases.DispositivoEstandar;
import Clases.DispositivoInteligente;
import Clases.EstadoDispositivoEnum;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class testDispositivo {

    public DispositivoEstandar unDispositivoEstandar;
    public DispositivoInteligente unDispositivoInteligente;

    @Before
    public void setUp() {
        unDispositivoEstandar = new DispositivoEstandar("a1", 300);
        unDispositivoInteligente = new DispositivoInteligente("da", 500);
    }

    @Test
    public void testConsumoTotal() {
        assertEquals(0.0, unDispositivoEstandar.getConsumoTotal());
    }

    @Test
    public void testConsultaDeEstadoDeUnDispositivoEstandar() {
        assertEquals("Como es estandar => Nulo", null, unDispositivoEstandar.estadoDispositivo());
    }

    @Test
    public void testConsultaDeConsumoTotalDeUnDispositivoEstandar() {
        assertEquals("Como es estandar => 0", 0.0, unDispositivoEstandar.getConsumoTotal());
    }

    @Test
    public void testConsultaConsumoDeUnDispositivoEstandar() {
        assertEquals("Como es estandar => tiene una estimacion", 300.0, unDispositivoEstandar.consumoEstimadoPorHora());
    }

    @Test
    public void testDispositivoInteligenteEncendido() {
        unDispositivoInteligente.encender();
        assertEquals(EstadoDispositivoEnum.ENCENDIDO, unDispositivoInteligente.estadoDispositivo());
    }

    @Test
    public void testDispositivoInteligenteApagado() {
        unDispositivoInteligente.apagar();
        assertEquals(EstadoDispositivoEnum.APAGADO, unDispositivoInteligente.estadoDispositivo());
    }

    @Test
    public void testDispositivoInteligenteModoAhorro() {
        unDispositivoInteligente.modoAhorroDeEnergia();
        assertEquals(EstadoDispositivoEnum.MODOAHORRO, unDispositivoInteligente.estadoDispositivo());
    }

    @Test
    public void testDispositivoInteligenteEnergiaConsumidaDuranteLasUltimas3Horas() {
        unDispositivoInteligente.encender();
        unDispositivoInteligente.serUsado(10);
        assertEquals(5000.0, unDispositivoInteligente.consumoUltimasXHoras(89));
    }

}
