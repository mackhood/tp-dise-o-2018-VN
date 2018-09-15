package test.simplex;


import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.repositories.RepositorioDispositivo;
import dominio.simplexservice.RecomendacionParaHogarEficiente;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    @Before
    public void setUp() {
        aireAcondicionado3500 = RepositorioDispositivo.getInstance().traerDispositivoInteligenteDeNombreConcreto("aireAcondicionado", "De 3500 frigorias");
        System.out.println(aireAcondicionado3500.getRestriccionMinima());
        System.out.println(aireAcondicionado3500.getRestriccionMaxima());

        lampara11W = RepositorioDispositivo.getInstance().traerDispositivoInteligenteDeNombreConcreto("lampara", "De 11W");

        aireAcondicionado3500.setHorasDeUso(350);
        aireAcondicionado3500.encender();

        lampara11W.encender();

        lavarropas5kgAgua = RepositorioDispositivo.getInstance().traerDispositivoEstandarDeNombreConcreto("lavarropas", "Automatico de 5kg con calentamiento de agua");

        estandares.add(lavarropas5kgAgua);
        inteligentes.add(aireAcondicionado3500);
        inteligentes.add(lampara11W);

        unCliente = new Cliente("Ariel", "Galvan", "galvanariel97", new ID(TiposId.DNI, "40130179"),
                new Domicilio("asd", 1, 1, 'a'), 4444444, estandares, inteligentes);

        dispositivos.add(aireAcondicionado3500);
        dispositivos.add(lampara11W);
        dispositivos.add(lavarropas5kgAgua);


        //unCliente.activarAhorroAutomatico();
        //recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(unCliente);
        //recomendacionParaHogarEficiente.realizarRecomendacionParaLosDispositivosInteligentes();
    }

    @Test
    public void testAireAcondicionado3500Encendido() {
        Assert.assertEquals(true, aireAcondicionado3500.estaEncendido());

    }

    @Test
    public void testLamparaDe11wEstaEncendido() {
        Assert.assertEquals(true, lampara11W.estaEncendido());
    }

    @Test
    public void testResultadoFuncionEconomicaDelCliente() {
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(unCliente);
        Assert.assertEquals(750, recomendacionParaHogarEficiente.getResultadoDeLaFuncionEconomica(), 10);
    }

    /*Arreglar esto
    @Test
    public void testConsumoRecomendadoMaximoDispositivo1DelCliente() {
        Assert.assertEquals(360, unCliente.getTodosLosDispositivos().get(0).getHorasMaximaRecomendadaPorConsumo(), 15);
    }

    @Test
    public void testConsumoRecomendadoMaximoDispositivo2DelCliente() {
        Assert.assertEquals(360, unCliente.getTodosLosDispositivos().get(1).getHorasMaximaRecomendadaPorConsumo(), 15);
    }

    @Test
    public void testConsumoRecomendadoMaximoDispositivo3DelCliente() {
        Assert.assertEquals(30, unCliente.getTodosLosDispositivos().get(2).getHorasMaximaRecomendadaPorConsumo(), 15);
    }*/

    @Test
    public void testPrimerDispositivo() {
        Assert.assertEquals("aireAcondicionado", unCliente.getTodosLosDispositivos().get(0).getNombre());
    }

    @Test
    public void testSegundoDispositivo() {
        Assert.assertEquals("lampara", unCliente.getTodosLosDispositivos().get(1).getNombre());
    }

    @Test
    public void testTercerDispositivo() {
        Assert.assertEquals("lavarropas", unCliente.getTodosLosDispositivos().get(2).getNombre());
    }

    @Test
    public void testCoefAireAcondicionado3500() {
        Assert.assertEquals(1.613, RepositorioDispositivo.getInstance().coefConsumoKwhDispositivo(aireAcondicionado3500), 0);
    }

    @Test
    public void testCoefLampara11W() {
        Assert.assertEquals(0.011, RepositorioDispositivo.getInstance().coefConsumoKwhDispositivo(lampara11W), 0);
    }
}
