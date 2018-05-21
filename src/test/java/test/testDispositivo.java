package test;

import Clases.*;
import Clases.Dispositivo.DispositivoEstandar;
import Clases.Dispositivo.DispositivoInteligente;
import Clases.Dispositivo.EstadoDispositivo;

import Clases.Regla.Regla;
import Clases.Sensor.Sensor;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.spy;

public class testDispositivo {

    public DispositivoEstandar unDispositivoEstandar;
    public DispositivoInteligente unDispositivoInteligente;
    private SubirIntensidad subirIntensidad;
    private Sensor sensorMedirTemperaturaAfuera;
    private Regla regla1;
    private Sensor sensorMovimiento;
    private Fabricante fabricante;

    @Before
    public void setUp() {

        unDispositivoEstandar = new DispositivoEstandar("a1", 300);
        unDispositivoInteligente = new DispositivoInteligente("da", 500);
        fabricante = new Fabricante(1);
        sensorMedirTemperaturaAfuera = new Sensor("MedirTemperaturaAfuera");
        sensorMovimiento = new Sensor("MedirMovimiento");
        subirIntensidad = new SubirIntensidad(unDispositivoInteligente, fabricante);
        regla1 = new Regla(subirIntensidad, sensorMedirTemperaturaAfuera);
    }

    @Test
    public void testSensorIntensidadConRegla1() {
        sensorMedirTemperaturaAfuera.comunicarMedicion(regla1);
        assertEquals(650.0, unDispositivoInteligente.consumoEstimadoPorHora());
    }


    @Test
    public void testSensorMovimientoConRegla1() {
        sensorMovimiento.comunicarMedicion(regla1);
        assertEquals(500.0, unDispositivoInteligente.consumoEstimadoPorHora());
    }

    @Test
    public void testConsultaDeEstadoDeUnDispositivoEstandar() {
        assertEquals("Como es estandar => Nulo", null, unDispositivoEstandar.estadoDispositivo());
    }

    @Test
    public void testConsumoTotal() {
        assertEquals(0.0, unDispositivoEstandar.getConsumoTotal());
    }

    @Test
    public void testConsultaDeConsumoTotalDeUnDispositivoEstandar() {

        assertEquals(0.0, unDispositivoEstandar.getConsumoTotal());
    }

    @Test
    public void testConsultaConsumoDeUnDispositivoPorHoraEstandar() {
        assertEquals(300.0, unDispositivoEstandar.consumoEstimadoPorHora());
    }

    @Test
    public void testDispositivoInteligenteEncendido() {
        unDispositivoInteligente.encender();
        assertEquals(EstadoDispositivo.ENCENDIDO, unDispositivoInteligente.estadoDispositivo());
    }


    @Test
    public void testDispositivoInteligenteModoAhorro() {
        unDispositivoInteligente.modoAhorroDeEnergia();
        assertEquals(EstadoDispositivo.MODOAHORRO, unDispositivoInteligente.estadoDispositivo());
    }

    @Test
    public void testDispositivoInteligenteEnergiaConsumidaDuranteLasUltimas3Horas() {
        unDispositivoInteligente.encender();
        unDispositivoInteligente.serUsado(10);
        assertEquals(5000.0, unDispositivoInteligente.consumoUltimasXHoras(89));
    }

}
