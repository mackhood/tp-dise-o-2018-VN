package test.simplex;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.dispositivo.TipoDispositivo;
import dominio.simplexservice.RecomendacionParaHogarEficiente;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.spy;

public class testUnitarioRecomendacion {
    DispositivoInteligente dispInteligente1 = mock(DispositivoInteligente.class);
    DispositivoInteligente dispInteligente2 = mock(DispositivoInteligente.class);
    DispositivoEstandar dispEstandar1 = mock(DispositivoEstandar.class);
    DispositivoEstandar dispEstandar2 = mock(DispositivoEstandar.class);
    Cliente cliente = mock(Cliente.class);

    List<DispositivoInteligente> inteligentes = new ArrayList<>();
    List<DispositivoEstandar> estandares = new ArrayList<>();

    List<Intervalo> intervalos = new ArrayList<>();
    @Before
    public void setUp(){

        dispInteligente1  = spy(new DispositivoInteligente.DispositivoInteligenteBuilder("dispInteligente1").consumoEstimadoPorHora(1.0).tipoDispositivo(new TipoDispositivo("tipo1",90,360)).intervalosDeUso(intervalos).build());
        dispInteligente2  = spy(new DispositivoInteligente.DispositivoInteligenteBuilder("dispInteligente2").consumoEstimadoPorHora(0.5).tipoDispositivo(new TipoDispositivo("tipo2",3,30)).intervalosDeUso(intervalos).build());

        dispEstandar1 = spy(new DispositivoEstandar.DispositivoEstandarBuilder("dispEstandar1").consumoEstimadoPorHora(1.0).tipoDispositivo(new TipoDispositivo("tipo3",5,10)).build());
        dispEstandar1 = spy(new DispositivoEstandar.DispositivoEstandarBuilder("dispEstandar2").consumoEstimadoPorHora(1.0).tipoDispositivo(new TipoDispositivo("tipo4",10,20)).build());

        Mockito.when(dispInteligente1.getConsumoTotal()).thenReturn(5000.0);
        Mockito.when(dispInteligente2.getConsumoTotal()).thenReturn(3000.0);

        estandares.add(dispEstandar1);
        inteligentes.add(dispInteligente1);
        inteligentes.add(dispInteligente2);
        dispInteligente1.encender();
        dispInteligente2.encender();

        cliente = spy(new Cliente("nombreTest", "apellidoTest", "usuarioTest","password", new ID(TiposId.DNI, "200"),
                new Domicilio("calleTest", 3118, 1, 'a'), 250, estandares, inteligentes));

    }

    @Test
    public void testEjecutarRecomendacionHogarDispInteligente1Apagado()
    {
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(cliente);
        recomendacionParaHogarEficiente.realizarRecomendacionParaLosDispositivosInteligentes();
        Assert.assertEquals(true,dispInteligente1.estaApagado());
    }
    @Test
    public void testEjecutarRecomendacionHogarDispInteligente2Apagado()
    {
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(cliente);
        recomendacionParaHogarEficiente.realizarRecomendacionParaLosDispositivosInteligentes();
        Assert.assertEquals(true,dispInteligente2.estaApagado());
    }
    @Test
    public void testFuncionEconomica()
    {
        RecomendacionParaHogarEficiente recomendacionParaHogarEficiente = new RecomendacionParaHogarEficiente(cliente);
        Assert.assertEquals(410.0,recomendacionParaHogarEficiente.getResultadoDeLaFuncionEconomica(),0);
    }
}
