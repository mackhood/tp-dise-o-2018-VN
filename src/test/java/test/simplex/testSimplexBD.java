package test.simplex;


import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.manager.ClienteManager;
import dominio.manager.DispositivosManager;
import dominio.repositories.RepositorioDispositivo;
import dominio.simplexservice.RecomendacionParaHogarEficiente;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;

public class testSimplexBD {

    DispositivoInteligente aireAcondicionado3500;
    DispositivoInteligente lampara11W;
    DispositivoEstandar lavarropas5kgAgua;
    Cliente unCliente;
    List<Dispositivo> dispositivos = new ArrayList<>();
    List<DispositivoInteligente> inteligentes = new ArrayList<>();
    List<DispositivoEstandar> estandares = new ArrayList<>();

    @Before
    public void setUp() {
        aireAcondicionado3500 = DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(new Long(10));

        lampara11W = DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(new Long(22));

        aireAcondicionado3500.setHorasDeUso(900);
        aireAcondicionado3500.encender();

        lampara11W.encender();

        lavarropas5kgAgua = DispositivosManager.getInstance().getDispositivoEstandarDeLaBD(new Long(5));

        estandares.add(lavarropas5kgAgua);
        inteligentes.add(aireAcondicionado3500);
        inteligentes.add(lampara11W);

        unCliente = new Cliente("Ariel", "Galvan", "galvanariel97","password", new ID(TiposId.DNI, "40130179"),
                new Domicilio("asd", 1, 1, 'a'), 4444444, estandares, inteligentes);

        dispositivos.add(aireAcondicionado3500);
        dispositivos.add(lampara11W);
        dispositivos.add(lavarropas5kgAgua);

    }

    @Test
    public void test()
    {
        System.out.println(aireAcondicionado3500.getRestriccionMaxima());
        System.out.println(aireAcondicionado3500.getRestriccionMinima());

        System.out.println(lampara11W.getRestriccionMaxima());
        System.out.println(lampara11W.getRestriccionMinima());

        System.out.println(lavarropas5kgAgua.getRestriccionMaxima());
        System.out.println(lavarropas5kgAgua.getRestriccionMinima());


        System.out.println(unCliente.getDispositivosInteligentes().size());

        System.out.println(unCliente.getDispositivosInteligentes().get(0).getHorasDeUso());

        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(unCliente);

        System.out.println(recomendacionParaHogarEficiente.getHorasMaximaDeConsumoPorDispositivo().length);
        /*for(int i =0; i < recomendacionParaHogarEficiente.getHorasMaximaDeConsumoPorDispositivo().length ; i++)
        {
            System.out.println(recomendacionParaHogarEficiente.getHorasMaximaDeConsumoPorDispositivo()[i]);
        }*/



    }
    @Test
    public void testAireAcondicionado3500DespuesDeRealizarLaRecomendacionPorCadaDispositivoEstaApagadoPorqueSuperaLasHorasMaximasRecomendadas() {
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBB");
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(unCliente);
        System.out.println("AAAAAAAAAAAAAAAAA");

        recomendacionParaHogarEficiente.realizarRecomendacionParaLosDispositivosInteligentes();
        System.out.println("TTTTTTTTTTTTTTTTTTTTT");

        Assert.assertEquals(true, aireAcondicionado3500.estaApagado());

    }

    @Test
    public void testLamparaDe11WDespuesDeRealizarLaRecomendacionPorCadaDispositivoSigueEncendidoPorqueNoSuperaLasHorasMaximasRecomendadas() {
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(unCliente);
        recomendacionParaHogarEficiente.realizarRecomendacionParaLosDispositivosInteligentes();
        System.out.println("A");
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
