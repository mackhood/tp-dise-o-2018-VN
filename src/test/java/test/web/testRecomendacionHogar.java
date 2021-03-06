package test.web;

import controllers.DispositivoController;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.*;
import persistence.ClienteManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import spark.Spark;

import java.util.ArrayList;
import java.util.List;

public class testRecomendacionHogar extends AbstractPersistenceTest implements WithGlobalEntityManager {

    DispositivoInteligente aireAcondicionado3500;
    DispositivoInteligente lampara11W;
    DispositivoEstandar lavarropas5kgAgua;
    Cliente unCliente;
    List<Dispositivo> dispositivos = new ArrayList<>();
    List<DispositivoInteligente> inteligentes = new ArrayList<>();
    List<DispositivoEstandar> estandares = new ArrayList<>();

    @Before
    public void setUp() {

    }

    @Test
    public void testRecomendacion()
    {
        unCliente = ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario("galvanariel");
        DispositivoController dispositivoController = new DispositivoController();

    }
}
