package test;

import Clases.Dispositivo.Dispositivo;
import Clases.Dispositivo.DispositivoEstandar;
import Clases.Dispositivo.DispositivoInteligente;
import Clases.Simplex.Simplex;
import Clases.repositories.RepositorioDispositivo;
import Clases.repositories.Repositorios;
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

    List<Dispositivo> dispositivos = new ArrayList<>();


    @Before
    public void setUp() {
        aireAcondicionado3500 = new DispositivoInteligente("aireAcondicionado", "De 3500 frigorias", false);
        lampara11W = new DispositivoInteligente("lampara", "De 11W", true);
        lavarropas5kgAgua = new DispositivoEstandar("lavarropas","Automatico de 5kg con calentamiento de agua",false);

        dispositivos.add(aireAcondicionado3500);
        dispositivos.add(lampara11W);
        dispositivos.add(lavarropas5kgAgua);
        simplex = new Simplex();

        simplex.agregarDispositivo(aireAcondicionado3500);
        simplex.agregarDispositivo(lampara11W);
        simplex.agregarDispositivo(lavarropas5kgAgua);
        simplex.execute();
    }
    @Test
    public void testCoefAireAcondicionado3500()
    {
        Assert.assertEquals(1.613,Repositorios.dispositivos.coefConsumokwh(aireAcondicionado3500),0);
    }

    @Test
    public void testCoefLampara11W()
    {
        Assert.assertEquals(0.011,Repositorios.dispositivos.coefConsumokwh(lampara11W),0);
    }

    @Test
    public void testCoefDispositivos()
    {
        Assert.assertEquals(0.011,Repositorios.dispositivos.coeficientesDeConsumoKwh(dispositivos)[1],0);
    }
    @Test
    public void testCoefsInecuacionesAireAcondicionado()
    {
        Assert.assertEquals(1,Repositorios.dispositivosMinmax.coefsResctriccionDeUnDispositivo(aireAcondicionado3500)[0],0);
    }
    @Test
    public void testSizeCoefsInecuacionesAireAcondicionado()
    {
        Assert.assertEquals(8,Repositorios.dispositivosMinmax.coefsResctriccionDeUnDispositivo(aireAcondicionado3500).length,0);
    }
    @Test
    public void testResultadoFuncionEconomica() {

        Assert.assertEquals(9, simplex.getResultadoFuncionEconomica(), 0);
    }

}
