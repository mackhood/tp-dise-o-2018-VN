package test;

import dominio.actuador.*;
import dominio.consultores.ConsultaConsumoUltimasNHoras;
//import dominio.consultores.ConsultaEstaApagado;
//import dominio.consultores.ConsultaEstaEncendido;
import dominio.dispositivo.*;

import dominio.regla.Regla;
import dominio.sensor.*;
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

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertSame;
import static org.mockito.Mockito.spy;

public class testDispositivo {


    private DispositivoEstandar unDE;
    private DispositivoInteligente unDIApagado;
    private DispositivoInteligente unDIEncendido;
    private DispositivoInteligente unDETransformado;

    private ConsultaConsumoUltimasNHoras consultaConsumoUltimasNHoras;

    private OrdenApagarDI ordenApagarDI;
    private OrdenEncenderDI ordenEncenderDI;
    private OrdenPonerModoAhorro ordenPonerModoAhorro;
    private OrdenSubirIntensidad ordenSubirIntensidad;
    private OrdenEncenderDI ordenEncenderDITestRegla;

    private Regla reglaParaAumentarIntensidadAlAireAcondicionado;
    private Sensor sensorTemperaturaMayor30;
    private Sensor sensorHayMovimientoEnLaCasa;

    private Cliente unCliente;
    private Convertidor moduloAdaptador;

    private Regla reglaParaEncenderAlAireAcondicionado;
    Condicion hayMovimientoEnLaCasa;
    CondicionPorMayor mayorA30;
    DispositivoInteligente unDIAireApagado;
    List<DispositivoInteligente> listaInteligentesTestRegla = new ArrayList<>();

    
    @Before
    public void setUp() {

        unDE = new DispositivoEstandar("a1", 300);
        unDIApagado = new DispositivoInteligente("da", 500);
        unDIEncendido = new DispositivoInteligente("AireAcondicionado", 100);
        unDIEncendido.setConsumoEstimadoPorHora(23);
        unDIEncendido.setHorasDeUso(2);
        unDIEncendido.encender();

        //unDIEncendido.serUsado(10);
        List <DispositivoInteligente> listDispApagados= new ArrayList<>();
        listDispApagados.add(unDIApagado);

        List <DispositivoInteligente> listDispModoAhorro= new ArrayList<>();
        listDispModoAhorro.add(unDIApagado);

        List <DispositivoInteligente> listDispEncendidos= new ArrayList<>();
        listDispEncendidos.add(unDIEncendido);

        List <DispositivoEstandar> listaDispositivosEstandard = new ArrayList<>();
        List <DispositivoInteligente> listaDispositivosInteligentes = new ArrayList<>();

        listaDispositivosEstandard.add(unDE);


        unCliente = spy(new Cliente("Nicolas", "Sierra", "fer25", new ID(TiposId.DNI, "200"),
                new Domicilio("bariloche", 3118, 1, 'a'), 250, listaDispositivosEstandard, listaDispositivosInteligentes));

        //unDETransformado = unCliente.agregarModuloAdaptador(moduloAdaptador, unDE);





        ordenApagarDI = new OrdenApagarDI(listDispEncendidos);
        ordenEncenderDI = new OrdenEncenderDI(listDispApagados);
        ordenPonerModoAhorro = new OrdenPonerModoAhorro(listDispModoAhorro);
        ordenSubirIntensidad = new OrdenSubirIntensidad(listDispEncendidos);


        //Para probar la regla para encender el aire acondicionado
        unDIAireApagado = new DispositivoInteligente("AireAcondicionado", 50);
        listaInteligentesTestRegla.add(unDIAireApagado);
        ordenEncenderDITestRegla = new OrdenEncenderDI(listaInteligentesTestRegla);
        List<Condicion> listaCondicionesCumplir = new ArrayList<>();
        reglaParaEncenderAlAireAcondicionado = new Regla(ordenEncenderDITestRegla,listaCondicionesCumplir);
       // hayMovimientoEnLaCasa = new Condicion(reglaParaEncenderAlAireAcondicionado,1, "hayMovimientoEnLaCasa");
        mayorA30 = new CondicionPorMayor(reglaParaEncenderAlAireAcondicionado,30);
        // listaCondicionesCumplir.add(hayMovimientoEnLaCasa);
        listaCondicionesCumplir.add(mayorA30);
        sensorTemperaturaMayor30 = new Sensor(reglaParaEncenderAlAireAcondicionado);
        sensorHayMovimientoEnLaCasa = new Sensor(reglaParaEncenderAlAireAcondicionado);

        unDIEncendido.setConsumoEstimadoPorHora(100);
    }

    @Test
    public void testReglaParaEncenderAlAireAcondicionado(){
    //    sensorHayMovimientoEnLaCasa.recibirMedicion(1);
        sensorTemperaturaMayor30.recibirMedicion(35);
        Assert.assertEquals(true,unDIAireApagado.estaEncendido());

    }
    @Test
    public void testConsumoDIEncendidoLuegoApagado(){
        LocalDateTime horaEncendido = LocalDateTime.of(2018,6,8,15,30,30,100);
        LocalDateTime horaApagado = LocalDateTime.of(2018,6,8,21,25,30,100);
        unDIEncendido.setHoraEncendido(horaEncendido);
        unDIEncendido.setHoraApagado(horaApagado);
        unDIEncendido.sumarHorasDeUso(horaEncendido,horaApagado);
        //unDIEncendido.apagar();
        //Assert.assertEquals(0,horaEncendido.until(LocalDateTime.of(2018,8,6,20,45,30,100),ChronoUnit.HOURS));
        //Assert.assertEquals(0,horaEncendido.until(unDIEncendido.getHoraApagado(),ChronoUnit.HOURS));
        //Assert.assertEquals(0,unDIEncendido.getHoraApagado().until(horaEncendido,ChronoUnit.HOURS));
        Assert.assertEquals(700.0,unDIEncendido.getConsumoTotal(),10);
        //Assert.assertEquals(0,LocalDateTime.now());
    }
    @Test
    public void testDEUsadoPor5HorasConsumoTotal() {
        unDE.serUsado(5);
        assertEquals(1500.0, unDE.getConsumoTotal());
    }

    @Test
    public void testDETUsadoPor90HorasConsumoUltimas3Horas() {
        unDE.serUsado(1);
        DispositivoEstandarInteligente unDET = new DispositivoEstandarInteligente(unDE);
        unCliente.agregarModuloAdaptador(unDE);
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
    public void testDIApagadoSeEnciende() {
        ordenEncenderDI.ejecutar();
        assertEquals(true, unDIApagado.estaEncendido());
    }


    @Test
    public void testDIApagadoSePoneEnModoAhorro() {
        ordenPonerModoAhorro.ejecutar();
        assertEquals(true, unDIApagado.estaEnModoAhorro());
    }

    @Test
    public void testDIEncendidoSeApaga() {
        ordenApagarDI.ejecutar();
        assertEquals(true, unDIEncendido.estaApagado());
    }

}