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
import java.util.Arrays;
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

        lampara11W = RepositorioDispositivo.getInstance().traerDispositivoInteligenteDeNombreConcreto("lampara", "De 11W");

        aireAcondicionado3500.setHorasDeUso(900);
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

    }

    @Test
    public void testAireAcondicionado3500DespuesDeRealizarLaRecomendacionPorCadaDispositivoEstaApagadoPorqueSuperaLasHorasMaximasRecomendadas() {
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(unCliente);
        recomendacionParaHogarEficiente.realizarRecomendacionParaLosDispositivosInteligentes();
        Assert.assertEquals(true, aireAcondicionado3500.estaApagado());

    }
    @Test
    public void testLamparaDe11WDespuesDeRealizarLaRecomendacionPorCadaDispositivoSigueEncendidoPorqueNoSuperaLasHorasMaximasRecomendadas() {
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(unCliente);
        recomendacionParaHogarEficiente.realizarRecomendacionParaLosDispositivosInteligentes();
        Assert.assertEquals(true, lampara11W.estaEncendido());

    }


    @Test
    public void testResultadoFuncionEconomicaDelCliente() {
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(unCliente);
        Assert.assertEquals(750, recomendacionParaHogarEficiente.getResultadoDeLaFuncionEconomica(), 10);
    }

    @Test
    public void testHorasMaximasRecomendadasPorCadaDispositivoDelClienteCoincide() {
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(unCliente);
        double horasMaximasDeConsumoPorDispositivo[] = {360.0, 360.0, 30.0};

        Assert.assertEquals(true, Arrays.equals(horasMaximasDeConsumoPorDispositivo, recomendacionParaHogarEficiente.getHorasMaximaDeConsumoPorDispositivo()));
    }

}
