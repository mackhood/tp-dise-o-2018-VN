package test;

import Clases.*;
import Clases.Actuador.*;
import Clases.Dispositivo.*;

import Clases.Regla.Regla;
import Clases.Sensor.Medicion;
import Clases.Sensor.MedicionMovimiento;
import Clases.Sensor.MedicionTemperatura;
import Clases.Sensor.Sensor;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;
import static org.mockito.Mockito.spy;

public class testDispositivo {


    private DispositivoEstandar unDE;
    private DispositivoInteligente unDIApagado;
    private DispositivoInteligente unDIEncendido;
    private DispositivoInteligente unDETransformado;

    private ConsultaConsumoUltimasXHoras consultaConsumoUltimasXHoras;
    private ConsultaEstaApagado consultaEstaApagado;
    private ConsultaEstaEncendido consultaEstaEncendido;

    private OrdenApagarDI ordenApagarDI;
    private OrdenEncenderDI ordenEncenderDI;
    private OrdenPonerModoAhorro ordenPonerModoAhorro;
    private OrdenSubirIntensidad ordenSubirIntensidad;

    private Sensor sensorMedirTemperaturaAfuera;
    private Regla reglaParaAumentarIntensidadAlAireAcondicionado;
    private Sensor sensorTemperaturaAmbiente;
    private Fabricante fabricante;

    private MedicionMovimiento medicionMovimiento = new MedicionMovimiento(true);
    private MedicionTemperatura medicionTemperatura = new MedicionTemperatura(true);

    @Before
    public void setUp() {

        fabricante = new Fabricante(1);
        unDE = new DispositivoEstandar("a1", 300, fabricante);
        unDIApagado = new DispositivoInteligente("da", 500, fabricante);
        unDIEncendido = new DispositivoInteligente("AireAcondicionado", 100, fabricante);
        unDIEncendido.encender();
        unDIEncendido.serUsado(10);
        unDE.agregarAdaptadorInteligente();
        unDETransformado = unDE.getDispositivoEstandarInteligente();

        consultaEstaApagado = new ConsultaEstaApagado(unDIApagado);
        consultaEstaEncendido = new ConsultaEstaEncendido(unDIApagado);
        ordenApagarDI = new OrdenApagarDI(unDIEncendido);
        ordenEncenderDI = new OrdenEncenderDI(unDIApagado);
        ordenPonerModoAhorro = new OrdenPonerModoAhorro(unDIApagado);

        ordenSubirIntensidad = new OrdenSubirIntensidad(unDIEncendido);
        List<Medicion> listaMedicionesACumplir = new ArrayList<>();
        listaMedicionesACumplir.add(medicionMovimiento);
        listaMedicionesACumplir.add(medicionTemperatura);
        reglaParaAumentarIntensidadAlAireAcondicionado = new Regla(ordenSubirIntensidad,listaMedicionesACumplir);
        sensorTemperaturaAmbiente = new Sensor(reglaParaAumentarIntensidadAlAireAcondicionado);
    }

    @Test
    public void testSensorTemperaturaAmbienteTomaMediciones()
    {
        sensorTemperaturaAmbiente.tomarMedicion(medicionTemperatura);
        sensorTemperaturaAmbiente.tomarMedicion(medicionMovimiento);
        assertEquals(150.0, unDIEncendido.consumoEstimadoPorHora());
    }
    @Test
    public void testDEUsadoPor5HorasConsumoTotal() {
        unDE.serUsado(5);
        assertEquals(1500.0, unDE.getConsumoTotal());
    }

    @Test
    public void testDETUsadoPor90HorasConsumoUltimas3Horas() {
        unDETransformado.serUsado(90);
        consultaConsumoUltimasXHoras = new ConsultaConsumoUltimasXHoras(unDETransformado, 3);
        assertEquals(900.0, consultaConsumoUltimasXHoras.consultar());
    }

    @Test
    public void testDIEncendidoConsumoUltimas2Horas() {
        consultaConsumoUltimasXHoras = new ConsultaConsumoUltimasXHoras(unDIEncendido, 2);
        assertEquals(200.0, consultaConsumoUltimasXHoras.consultar());
    }

    @Test
    public void testDIEncendidoConsumoUltimas100Horas() {
        consultaConsumoUltimasXHoras = new ConsultaConsumoUltimasXHoras(unDIEncendido, 100);
        assertEquals(1000.0, consultaConsumoUltimasXHoras.consultar());
    }

    @Test
    public void testDEConsultaDeConsumoTotalDeUnDispositivoEstandarUsadoPor3Horas() {
        unDE.serUsado(3);
        assertEquals(900.0, unDE.getConsumoTotal());
    }

    @Test
    public void testDEConsultaConsumoDeUnDispositivoPorHoraEstandar() {
        assertEquals(300.0, unDE.consumoEstimadoPorHora());
    }

    @Test
    public void testDIApagadoSeEnciende() {
        ordenEncenderDI.ejecutar();
        assertEquals(true, unDIApagado.estaEncendido());
    }


    @Test
    public void testDIApagadoSePoneEnModoAhorro() {
        ordenPonerModoAhorro.ejecutar();
        assertEquals(false, unDIApagado.estaEnModoAhorro());
    }

    @Test
    public void testDIEncendidoSeApaga() {
        ordenApagarDI.ejecutar();
        assertEquals(true, unDIEncendido.estaApagado());
    }

}
