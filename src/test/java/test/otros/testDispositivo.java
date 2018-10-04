package test.otros;

import dominio.consultores.ConsultaConsumoUltimasNHoras;
import dominio.dispositivo.Conversor;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoEstandarInteligente;
import dominio.dispositivo.DispositivoInteligente;
import dominio.entities.NoTieneDispositivoException;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.spy;

public class testDispositivo {

    private DispositivoEstandar unDE;
    private DispositivoInteligente unDIApagado;
    private DispositivoInteligente unDIEncendido;
    private ConsultaConsumoUltimasNHoras consultaConsumoUltimasNHoras;
    private Cliente unCliente;
    private Conversor moduloAdaptador;

    @Before
    public void setUp() {

        unDE = new DispositivoEstandar.DispositivoEstandarBuilder("a1").consumoEstimadoPorHora((double) 300).build();
        unDIEncendido = new DispositivoInteligente.DispositivoInteligenteBuilder("da")
                .consumoEstimadoPorHora((double) 500).build();
        unDIApagado = new DispositivoInteligente.DispositivoInteligenteBuilder("AireAcondicionado")
                .consumoEstimadoPorHora((double) 100).build();
        unDIEncendido.setConsumoEstimadoPorHora(23);
        unDIEncendido.setHorasDeUso(2);
        unDIEncendido.encender();

        moduloAdaptador = new Conversor();

        // unDIEncendido.serUsado(10);
        List<DispositivoInteligente> listDispApagados = new ArrayList<>();
        listDispApagados.add(unDIApagado);

        List<DispositivoInteligente> listDispModoAhorro = new ArrayList<>();
        listDispModoAhorro.add(unDIApagado);

        List<DispositivoInteligente> listDispEncendidos = new ArrayList<>();
        listDispEncendidos.add(unDIEncendido);

        List<DispositivoEstandar> listaDispositivosEstandard = new ArrayList<>();
        List<DispositivoInteligente> listaDispositivosInteligentes = new ArrayList<>();

        listaDispositivosEstandard.add(unDE);

        unCliente = spy(new Cliente("Nicolas", "Sierra", "fer25", new ID(TiposId.DNI, "200"),
                new Domicilio("Bariloche", 3118, 1, 'a'), 250, listaDispositivosEstandard,
                listaDispositivosInteligentes));

        // unDETransformado = unCliente.agregarModuloAdaptador(moduloAdaptador, unDE);

        unDIEncendido.setConsumoEstimadoPorHora(100);

    }

    /*
     * En este test, quiero probar que al principio se cumplen ambas condiciones,
     * por lo que se ejecuta el actuador. Luego el sensor que verifica la
     * temperatura recibe una medicion que no cumple la condicion, por lo que el
     * actuador realiza la accion inversa (en este caso, vuelve a apagar el aire)
     *
     *
     * @Test public void testCambioDeEstado() {
     * sensorTemperaturaMayor30.recibirMedicion(new Medicion(32, "Temperatura"));
     * sensorMovimiento.recibirMedicion(new Medicion(1, "Movimiento"));
     * sensorTemperaturaMayor30.recibirMedicion(new Medicion(29, "Temperatura"));
     * Assert.assertEquals(false, unDIAireApagado.estaEncendido()); }
     */

    @Test
    public void testConsumoDIEncendidoLuegoApagado() {
        LocalDateTime horaEncendido = LocalDateTime.of(2018, 6, 8, 15, 30, 30, 100);
        LocalDateTime horaApagado = LocalDateTime.of(2018, 6, 8, 21, 25, 30, 100);
        unDIEncendido.setHoraEncendido(horaEncendido);
        unDIEncendido.setHoraApagado(horaApagado);
        unDIEncendido.sumarHorasDeUso(horaEncendido, horaApagado);
        Assert.assertEquals(700.0, unDIEncendido.getConsumoTotal(), 10);

        // unDIEncendido.apagar();
        // Assert.assertEquals(0,horaEncendido.until(LocalDateTime.of(2018,8,6,20,45,30,100),ChronoUnit.HOURS));
        // Assert.assertEquals(0,horaEncendido.until(unDIEncendido.getHoraApagado(),ChronoUnit.HOURS));
        // Assert.assertEquals(0,unDIEncendido.getHoraApagado().until(horaEncendido,ChronoUnit.HOURS));

        // Assert.assertEquals(0,LocalDateTime.now());
    }

    @Test
    public void testDEUsadoPor5HorasConsumoTotal() {
        unDE.serUsado(5);
        assertEquals(1500.0, unDE.getConsumoTotal());
    }

    @Test
    public void testDETUsadoPor90HorasConsumoUltimas3Horas() throws NoTieneDispositivoException {
        unDE.serUsado(1);
        DispositivoEstandarInteligente unDET = new DispositivoEstandarInteligente(unDE);
        unCliente.agregarModuloAdaptador(moduloAdaptador, unDE);
        consultaConsumoUltimasNHoras = new ConsultaConsumoUltimasNHoras(unDET, 3);
        assertEquals(300.0, consultaConsumoUltimasNHoras.consultar());
    }

    @Test
    public void testDIEncendidoConsumoUltimas2Horas() {
        consultaConsumoUltimasNHoras = new ConsultaConsumoUltimasNHoras(unDIEncendido, 2);
        assertEquals(200.0, consultaConsumoUltimasNHoras.consultar());
    }

    @Test
    public void testDIEncendidoConsumoUltimas10Horas() {
        unDIEncendido.apagar();
        consultaConsumoUltimasNHoras = new ConsultaConsumoUltimasNHoras(unDIEncendido, 10);

        assertEquals(200.0, consultaConsumoUltimasNHoras.consultar());
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
    public void testDECambioDeEstadoDispositivoInteligente() {
        unDIEncendido.apagar();
        assertFalse(unDIEncendido.estaEncendido());
    }

    @Test
    public void testDEModoAhorroDispositivoInteligente() {
        unDIEncendido.ponerModoAhorro();
        assertTrue(unDIEncendido.estaEnModoAhorro());
    }

    @Test
    public void testReducirConsumoDispositivoInteligenteYapagarlo() {
        unDIEncendido.reducirConsumoPor(100);
        assertTrue(unDIEncendido.estaApagado());
    }


}