package test.simplex;


import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.simplexservice.RecomendacionParaHogarEficiente;
import dominio.simplexservice.SimplexBuilder;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import persistence.DispositivosManager;
import datos.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.spy;

public class testIntegradoRecomendacion {

    DispositivoInteligente aireAcondicionado2200;
    DispositivoInteligente led40;
    DispositivoEstandar lavarropas5kgAgua;
    Cliente unCliente;
    List<Dispositivo> dispositivos = new ArrayList<>();
    List<DispositivoInteligente> inteligentes = new ArrayList<>();
    List<DispositivoEstandar> estandares = new ArrayList<>();

    @Before
    public void setUp() {
        Intervalo i1 = spy(new Intervalo(LocalDateTime.of(2018, 10, 12, 13, 10), LocalDateTime.of(3000, 10, 12, 22, 45)));

        aireAcondicionado2200 = spy(DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(new Long(19)));

        led40 = spy(DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(new Long(22)));

        aireAcondicionado2200.agregarIntervalo(i1);
        aireAcondicionado2200.encender();

        led40.encender();

        lavarropas5kgAgua = spy(DispositivosManager.getInstance().getDispositivoEstandarDeLaDPorEquipoConcreto("Automatico de 5kg con calentamiento de agua"));

        estandares.add(lavarropas5kgAgua);
        inteligentes.add(aireAcondicionado2200);
        inteligentes.add(led40);

        unCliente = spy(new Cliente("Ariel", "Galvan", "galvanariel97","password", new ID(TiposId.DNI, "40130179"),
                new Domicilio("asd", 1, 1, 'a'), 4444444, estandares, inteligentes));

        dispositivos.add(aireAcondicionado2200);
        dispositivos.add(led40);
        dispositivos.add(lavarropas5kgAgua);

    }

    @Test
    public void testConsumoTotalAireAcondicionado()
    {
        Assert.assertEquals(3000,aireAcondicionado2200.getConsumoTotal(),10000000);
    }
    @Test
    public void testAireAcondicionado3500DespuesDeRealizarLaRecomendacionPorCadaDispositivoEstaApagadoPorqueSuperaLasHorasMaximasRecomendadas() {
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(unCliente);
        recomendacionParaHogarEficiente.realizarRecomendacionParaLosDispositivosInteligentes();

        Assert.assertEquals(true, aireAcondicionado2200.estaApagado());

    }

    @Test
    public void testLamparaDe11WDespuesDeRealizarLaRecomendacionPorCadaDispositivoSigueEncendidoPorqueNoSuperaLasHorasMaximasRecomendadas() {
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(unCliente);
        recomendacionParaHogarEficiente.realizarRecomendacionParaLosDispositivosInteligentes();
        Assert.assertEquals(true, led40.estaEncendido());

    }


    @Test
    public void testDispositivosInteligentesQueSeVanAApagar()
    {
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(unCliente);
        Map<DispositivoInteligente, Double> map = recomendacionParaHogarEficiente.getHorasMaximaDeConsumoPorDispositivoInteligenteQueSupereLasHorasMaximas();

        Map.Entry<DispositivoInteligente,Double> entry = map.entrySet().iterator().next();
        DispositivoInteligente key = entry.getKey();

        Assert.assertEquals("Aire Acondicionado", key.getNombre());
    }

    @Test
    public void testResultadoFuncionEconomicaDelCliente() {
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(unCliente);
        Assert.assertEquals(750, recomendacionParaHogarEficiente.getResultadoDeLaFuncionEconomica(), 10);
    }


}
