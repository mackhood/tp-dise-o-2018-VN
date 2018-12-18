package test.web;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.repositories.RepositorioDispositivo;
import dominio.usuario.*;
import persistence.ClienteManager;
import persistence.DispositivosManager;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import utils.RequestUtil;
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


        //lampara11W = DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(new Long(22));


        DispositivoInteligente aireAcondicionado3500 = unCliente.getDispositivosInteligentes().get(0);
        aireAcondicionado3500.setHorasDeUso(900);
        aireAcondicionado3500.encender();

        DispositivoInteligente lampara11W = unCliente.getDispositivosInteligentes().get(1);
        lampara11W.encender();

        entityManager().persist(aireAcondicionado3500);
        entityManager().persist(lampara11W);



    }
}
