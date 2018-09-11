package test;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.EstadoEncendido;
import dominio.simplexservice.Recomendacion;
import dominio.simplexservice.VectorSimplex;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.repositories.Repositorios;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class testSimplex {

    DispositivoInteligente aireAcondicionado3500;
    DispositivoInteligente lampara11W;
    DispositivoEstandar lavarropas5kgAgua;
    Cliente unCliente;
    List<Dispositivo> dispositivos = new ArrayList<>();
    List<DispositivoInteligente> inteligentes = new ArrayList<>();
    List<DispositivoEstandar> estandares = new ArrayList<>();
    Recomendacion recomendacion;

    @Before
    public void setUp() {
        aireAcondicionado3500 = new DispositivoInteligente.DispositivoInteligenteBuilder("aireAcondicionado").equipoConcreto("De 3500 frigorias").esBajoConsumo(false).consumoEstimadoPorHora((double) 1.613).build();
        lampara11W = new DispositivoInteligente.DispositivoInteligenteBuilder("lampara").equipoConcreto("De 11W").esBajoConsumo(true).consumoEstimadoPorHora((double) 0.011).build();

        aireAcondicionado3500.setHorasDeUso(350);
        aireAcondicionado3500.encender();

        lampara11W.encender();


        lavarropas5kgAgua = new DispositivoEstandar.DispositivoEstandarBuilder("lavarropas")
                .equipoConcreto("Automatico de 5kg con calentamiento de agua").esBajoConsumo(false)
                .consumoEstimadoPorHora(0.005).build();

        estandares.add(lavarropas5kgAgua);
        inteligentes.add(aireAcondicionado3500);
        inteligentes.add(lampara11W);

        unCliente = new Cliente("Ariel", "Galvan", "galvanariel97", new ID(TiposId.DNI, "40130179"),
                new Domicilio("asd", 1, 1, 'a'), 4444444, estandares, inteligentes);

        dispositivos.add(aireAcondicionado3500);
        dispositivos.add(lampara11W);
        dispositivos.add(lavarropas5kgAgua);


        unCliente.activarAhorroAutomatico();
        recomendacion = new Recomendacion(unCliente);
        recomendacion.realizarRecomendacionParaLosDispositivosInteligentes();
    }

    @Test
    public void testAireAcondicionado3500EstaApagado() {
        Assert.assertEquals(true, aireAcondicionado3500.estaApagado());

    }

    @Test
    public void testLamparaDe11wEstaEncendido() {
        Assert.assertEquals(true, lampara11W.estaEncendido());
    }

    @Ignore
    public void testConsumoRecomendadoMaximoParaCadaDispositivoDelCliente() {
        unCliente.todosLosDispositivos().stream().forEach(dispositivo -> System.out.println(dispositivo.getHorasMaximaRecomendadaPorConsumo()));
    }

    @Test
    public void testResultadoFuncionEconomicaDelCliente() {
        Assert.assertEquals(750, recomendacion.getResultadoDeLaFuncionEconomica(), 10);
    }

    @Test
    public void testConsumoRecomendadoMaximoDispositivo1DelCliente() {
        Assert.assertEquals(360, unCliente.todosLosDispositivos().get(0).getHorasMaximaRecomendadaPorConsumo(), 15);
    }

    @Test
    public void testConsumoRecomendadoMaximoDispositivo2DelCliente() {
        Assert.assertEquals(360, unCliente.todosLosDispositivos().get(1).getHorasMaximaRecomendadaPorConsumo(), 15);
    }

    @Test
    public void testConsumoRecomendadoMaximoDispositivo3DelCliente() {
        Assert.assertEquals(30, unCliente.todosLosDispositivos().get(2).getHorasMaximaRecomendadaPorConsumo(), 15);
    }

    @Test
    public void testPrimerDispositivo() {
        Assert.assertEquals("aireAcondicionado", unCliente.todosLosDispositivos().get(0).getNombre());
    }

    @Test
    public void testSegundoDispositivo() {
        Assert.assertEquals("lampara", unCliente.todosLosDispositivos().get(1).getNombre());
    }

    @Test
    public void testTercerDispositivo() {
        Assert.assertEquals("lavarropas", unCliente.todosLosDispositivos().get(2).getNombre());
    }

    @Test
    public void testCoefAireAcondicionado3500() {
        Assert.assertEquals(1.613, Repositorios.dispositivos.coefConsumoKwhDispositivo(aireAcondicionado3500), 0);
    }

    @Test
    public void testCoefLampara11W() {
        Assert.assertEquals(0.011, Repositorios.dispositivos.coefConsumoKwhDispositivo(lampara11W), 0);
    }
}
