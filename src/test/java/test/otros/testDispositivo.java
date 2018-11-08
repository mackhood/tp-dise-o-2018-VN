package test.otros;

import dominio.consultores.ConsultaConsumoUltimasNHoras;
import dominio.dispositivo.Conversor;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoEstandarInteligente;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.EstadoApagado;
import dominio.dispositivo.EstadoEncendido;
import dominio.dispositivo.Intervalo;
import dominio.entities.NoTieneDispositivoException;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
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
    private Intervalo i1;
    private Intervalo i2;
    private Intervalo i3;
    private Intervalo i4;
    private Intervalo i5;
    private Intervalo i6;
    private Intervalo i7;
    private Intervalo i8;
    
    @Before
    public void setUp() {

        unDE = new DispositivoEstandar.DispositivoEstandarBuilder("a1").consumoEstimadoPorHora((double) 300).build();
        
        moduloAdaptador = new Conversor();
        
        i1 = spy(new Intervalo(LocalDateTime.of(2018,05,12,4,25), LocalDateTime.of(2018,05,12,9,30)));
        i2 = spy(new Intervalo(LocalDateTime.of(2018,05,18,6,00), LocalDateTime.of(2018,05,18,14,20)));
        i3 = spy(new Intervalo(LocalDateTime.of(2018,05,20,18,30), LocalDateTime.of(2018,05,20,20,30)));
        i4 = spy(new Intervalo(LocalDateTime.of(2018,05,29,13,20), LocalDateTime.of(2018,05,29,14,20)));
        i5 = spy(new Intervalo(LocalDateTime.of(2018,06,03,12,10), LocalDateTime.of(2018,06,04,14,30)));
        i6 = spy(new Intervalo(LocalDateTime.of(2018,06,20,00,20), LocalDateTime.of(2018,06,20,7,20)));
        i7 = spy(new Intervalo(LocalDateTime.now().minusHours(10), LocalDateTime.now().minusNanos(1)));
        i8 = spy(new Intervalo(LocalDateTime.of(2018, 06,03,13,00),LocalDateTime.of(2018,06,03,15,00)));
        
        List <Intervalo> intervalosDeUso = new ArrayList<>();
        intervalosDeUso.add(i3);
        
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

        unCliente = spy(new Cliente("Nicolas", "Sierra", "fer25","password", new ID(TiposId.DNI, "200"),
                new Domicilio("Bariloche", 3118, 1, 'a'), 250, listaDispositivosEstandard,
                listaDispositivosInteligentes));

        // unDETransformado = unCliente.agregarModuloAdaptador(moduloAdaptador, unDE);
        
        unDIEncendido = new DispositivoInteligente.DispositivoInteligenteBuilder("da")
                .consumoEstimadoPorHora((double) 500).intervalosDeUso(intervalosDeUso).build();
        unDIApagado = new DispositivoInteligente.DispositivoInteligenteBuilder("AireAcondicionado").estadoDispositivo(new EstadoApagado())
                .consumoEstimadoPorHora((double) 100).intervalosDeUso(intervalosDeUso).build();
    }

    @Test
    public void testCambioDeEstadoDispositivoInteligente() {
        unDIEncendido.encender();
        unDIEncendido.apagar();
        assertFalse(unDIEncendido.estaEncendido());
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
    public void testConsumoParaIntervaloDe2Horas() {
        
        Assert.assertEquals(1000.0, unDIEncendido.consumoParaIntervalo(i3),1);
    }

    @Test
    public void testDEUsadoPor5HorasConsumoTotal() {
        unDE.serUsado(5);
        assertEquals(1500.0, unDE.getConsumoTotal());
    }

    @Test
    public void testDETUsadoPor90HorasConsumoUltimas3Horas() throws NoTieneDispositivoException {
   
        DispositivoEstandarInteligente unDET = new DispositivoEstandarInteligente(unDE);
        consultaConsumoUltimasNHoras = new ConsultaConsumoUltimasNHoras(unDET, 3);
        assertEquals(0.0, consultaConsumoUltimasNHoras.consultar());
    }

    @Test
    public void testDIEncendidoConsumoUltimas2Horas() {
        consultaConsumoUltimasNHoras = new ConsultaConsumoUltimasNHoras(unDIEncendido, 2);
        assertEquals(0.0, consultaConsumoUltimasNHoras.consultar());
    }

    
    @Test
    public void testDIEncendidoConsumoUltimas10Horas() {
    	
    	unDIEncendido.agregarIntervalo(i7);
        assertEquals(4500.0, unDIEncendido.consumoUltimasNHoras(10));
    }
    
    @Test
    public void testIntervaloCaeDentroDeOtro() {
    	
    	assertTrue(i8.caeDentroDe(i5));
    }
    
    @Test
    public void testConsumoTotal() {
    	// i3 agregado en setUp = 2 horas
    	unDIEncendido.agregarIntervalo(i2); // 8 horas
    	unDIEncendido.agregarIntervalo(i5); // 26 horas
    	assertEquals(18000.0,unDIEncendido.getConsumoTotal());
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
    public void testDEModoAhorroDispositivoInteligente() {
        unDIEncendido.ponerModoAhorro();
        assertTrue(unDIEncendido.estaEnModoAhorro());
    }

    @Test
    public void testReducirConsumoDispositivoInteligente() {
        unDIEncendido.reducirConsumoPor(100);
        assertEquals(400.0,unDIEncendido.consumoEstimadoPorHora(),1);
    }


}