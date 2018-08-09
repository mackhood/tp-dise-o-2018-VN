package test;

import Clases.Dispositivo.Dispositivo;
import Clases.Dispositivo.DispositivoEstandar;
import Clases.Dispositivo.DispositivoInteligente;
import Clases.Simplex.Simplex;
import Clases.Transformador.Transformador;
import Clases.Usuario.Cliente;
import Clases.Usuario.Domicilio;
import Clases.Usuario.ID;
import Clases.Usuario.TiposId;
import Clases.ZonaGeografica.Ubicacion;
import Clases.ZonaGeografica.ZonaGeografica;
import Clases.repositories.RepositorioDispositivo;
import Clases.repositories.Repositorios;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class testParaZonaTransformadores{

    private Cliente clienteMock1;
    private Cliente clienteMock2;
    private Cliente clienteMock3;

    private Transformador transformador1_Zona1;
    private Transformador transformador2_Zona1;

    private Transformador transformador_OtraZona;

    private ZonaGeografica zonaGeografica1;
    private ZonaGeografica zonaGeografica2;

    private List<Transformador> listaTransformadorZona1 = new ArrayList<>();;
    @Before
    public void setUp() {

        clienteMock1 = mock(Cliente.class);
        clienteMock2 = mock(Cliente.class);
        clienteMock3 = mock(Cliente.class);

        transformador_OtraZona = mock(Transformador.class);


        List<Transformador> listaTransformadorOtraZona = new ArrayList<>();
        listaTransformadorOtraZona.add(transformador_OtraZona);




        List<Cliente> listaClientesTransformador1 = new ArrayList<>();
        List<Cliente> listaClientesTransformador2 = new ArrayList<>();

        listaClientesTransformador1.add(clienteMock1);
        listaClientesTransformador1.add(clienteMock2);

        listaClientesTransformador2.add(clienteMock3);


        transformador1_Zona1 = new Transformador(zonaGeografica1,listaClientesTransformador1,new Ubicacion(20.0,20.0),40);
        transformador2_Zona1 = new Transformador(zonaGeografica1,listaClientesTransformador2,new Ubicacion(10.0,10.0),5);


        when(clienteMock1.consumoEnergeticoTotal()).thenReturn(350.0);
        when(clienteMock2.consumoEnergeticoTotal()).thenReturn(400.0);
        when(clienteMock3.consumoEnergeticoTotal()).thenReturn(200.0);

        when(transformador_OtraZona.energiaConsumidaClientes()).thenReturn(600.0);


        listaTransformadorZona1.add(transformador1_Zona1);
        listaTransformadorZona1.add(transformador2_Zona1);

        zonaGeografica1 = new ZonaGeografica(listaTransformadorZona1);
        zonaGeografica2 = new ZonaGeografica(listaTransformadorOtraZona);
    }


    @Test
    //tengo un problema con el equals (double,double) por eso cree este metodo
    public void testConsumoTransformadorMayorA700()
    {
       //assertEquals(750.0,transformador1_Zona1.energiaConsumidaClientes());
       assertTrue(transformador1_Zona1.energiaMayorA700());
    }

    @Test
    public void testConsumoTransformadorMenorA700()
    {
        //assertEquals(750.0,transformador1_Zona1.energiaConsumidaClientes());
        assertFalse(transformador2_Zona1.energiaMayorA700());
    }

    @Test
    public void testConsumoZona()
    {
        //assertEquals(750.0,transformador1_Zona1.energiaConsumidaClientes());
        //assertNotNull(zonaGeografica1.getTransformadores());
        //assertEquals(listaTransformadorZona1,zonaGeografica1.getTransformadores());

        //assertEquals(transformador1_Zona1,zonaGeografica1.getTransformadores().get(0));

        // Consumo transformador 1 = 750 + conssumo transformador 2 = 200 -> 950
        assertTrue(zonaGeografica1.energiaMayorA900());

    }



}

