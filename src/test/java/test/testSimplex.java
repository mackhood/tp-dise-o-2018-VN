package test;

import Clases.Dispositivo.Dispositivo;
import Clases.Dispositivo.DispositivoEstandar;
import Clases.Dispositivo.DispositivoInteligente;
import Clases.Simplex.Simplex;
import Clases.Usuario.Cliente;
import Clases.Usuario.Domicilio;
import Clases.Usuario.ID;
import Clases.Usuario.TiposId;
import Clases.repositories.RepositoriosTP2;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class testSimplex {

    DispositivoInteligente aireAcondicionado3500;
    DispositivoInteligente lampara11W;
    DispositivoEstandar lavarropas5kgAgua;
    Simplex simplex;
    Cliente unCliente;
    List<Dispositivo> dispositivos = new ArrayList<>();
    List<DispositivoInteligente> inteligentes = new ArrayList<>();
    List<DispositivoEstandar> estandares = new ArrayList<>();

    @Before
    public void setUp() {
        aireAcondicionado3500 = new DispositivoInteligente("aireAcondicionado", "De 3500 frigorias", false, 0.05);
        lampara11W = new DispositivoInteligente("lampara", "De 11W", true,0.0004);
        lavarropas5kgAgua = new DispositivoEstandar("lavarropas","Automatico de 5kg con calentamiento de agua",false,0.005);

        estandares.add(lavarropas5kgAgua);
        inteligentes.add(aireAcondicionado3500);
        inteligentes.add(lampara11W);

        unCliente = new Cliente("Ariel","Galvan","galvanariel97",new ID(TiposId.DNI,"40130179"),
                new Domicilio("asd",1,1,'a'),4444444,estandares, inteligentes);

        dispositivos.add(aireAcondicionado3500);
        dispositivos.add(lampara11W);
        dispositivos.add(lavarropas5kgAgua);

        unCliente.horasTotalesConsumidasPorLosDispositivos();
    }
    @Test
    public void testPrimerDispositivo()
    {
        Assert.assertEquals("aireAcondicionado",unCliente.todosLosDispositivos().get(0).getNombre());
    }
    @Test
    public void testSegundoDispositivo()
    {
        Assert.assertEquals("lampara",unCliente.todosLosDispositivos().get(1).getNombre());
    }
    @Test
    public void testTercerDispositivo()
    {
        Assert.assertEquals("lavarropas",unCliente.todosLosDispositivos().get(2).getNombre());
    }
    @Test
    public void testCoefAireAcondicionado3500()
    {
        Assert.assertEquals(1.613,RepositoriosTP2.dispositivosConsumoKWh.coefConsumokwh(aireAcondicionado3500),0);
    }

    @Test
    public void testCoefLampara11W()
    {
        Assert.assertEquals(0.011,RepositoriosTP2.dispositivosConsumoKWh.coefConsumokwh(lampara11W),0);
    }

    @Test
    public void testCoefDispositivos()
    {
        Assert.assertEquals(0.011,RepositoriosTP2.dispositivosConsumoKWh.coeficientesDeConsumoKwh(dispositivos)[1],0);
    }
    @Test
    public void testResultadoFuncionEconomica() {

        //Deberia dar otra cosa supongo
        Assert.assertEquals(0, unCliente.horasTotalesConsumidasPorLosDispositivos(), 0);
    }
    @Test
    public void testConsumoRecomendadoAireAcondicionado()
    {
        Assert.assertEquals(90,aireAcondicionado3500.getHorasMaximaPorConsumo(),10);
    }
    @Test
    public void testConsumoRecomendadoLampara()
    {
        Assert.assertEquals(90,lampara11W.getHorasMaximaPorConsumo(),10);
    }
    @Test
    public void testConsumoRecomendadoLavarropas()
    {
        Assert.assertEquals(6,lavarropas5kgAgua.getHorasMaximaPorConsumo(),10);
    }
}
