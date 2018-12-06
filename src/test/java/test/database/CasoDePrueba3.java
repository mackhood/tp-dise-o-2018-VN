package test.database;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.actuador.OrdenEncenderDI;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.regla.Regla;
import dominio.sensor.Condicion;
import dominio.sensor.CondicionPorMayor;
import dominio.sensor.CondicionPorMenor;

public class CasoDePrueba3 extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Before
    public void setup() {
        entityManager().getTransaction().begin();
    }

    @Test
    public void casoDePrueba3() {

        List<Intervalo> intervalosDeUso = new ArrayList<>();
        DispositivoInteligente di = new DispositivoInteligente.DispositivoInteligenteBuilder("di").consumoEstimadoPorHora((double) 200).intervalosDeUso(intervalosDeUso).build();
        OrdenEncenderDI actuador = new OrdenEncenderDI(di);
        List<Condicion> condiciones = new ArrayList<>();
        Regla reglaTest = new Regla(actuador, condiciones);
        CondicionPorMayor mayorA20 = new CondicionPorMayor(20, "Temperatura");
        mayorA20.asociarA(reglaTest);

        entityManager().persist(di);
        entityManager().persist(actuador);
        entityManager().persist(reglaTest);
        entityManager().persist(mayorA20);

        Regla reglaRecuperada = entityManager().createQuery("from Regla r", Regla.class).getSingleResult();
        reglaRecuperada.chequearCondicionesYEjecutar();
        CondicionPorMenor menorA30 = new CondicionPorMenor(15, "Temperatura");
        menorA30.asociarA(reglaRecuperada);
        entityManager().persist(reglaRecuperada);
        Regla reglaRecuperadaV2 = (Regla) entityManager().createQuery("from Regla r").getSingleResult();
        assertTrue(reglaRecuperadaV2.getCondicionesACumplir().contains(menorA30));
    }

    @After
    public void rollback() {

        entityManager().getTransaction().rollback();
    }
}
