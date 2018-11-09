package test.database;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import dominio.actuador.OrdenEncenderDI;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.regla.Regla;
import dominio.sensor.Condicion;
import dominio.sensor.CondicionPorMayor;
import dominio.sensor.CondicionPorMenor;

public class testReglaCondicion implements WithGlobalEntityManager {

    @Test
    public void casoDePrueba3() {

    	List<Intervalo> intervalosDeUso = new ArrayList<>();
    	DispositivoInteligente di = new DispositivoInteligente.DispositivoInteligenteBuilder("di").consumoEstimadoPorHora((double)200).intervalosDeUso(intervalosDeUso).build();
    	OrdenEncenderDI actuador = new OrdenEncenderDI(di);
    	List<Condicion> condiciones = new ArrayList<>();
    	Regla reglaTest = new Regla(actuador,condiciones);
    	CondicionPorMayor mayorA20 = new CondicionPorMayor(20,"Temperatura");
    	mayorA20.asociarA(reglaTest);
    	condiciones.add(mayorA20);
    	
    	entityManager().persist(di);
    	entityManager().persist(actuador);
    	entityManager().persist(reglaTest);
    	entityManager().persist(mayorA20);
    	
    	Regla reglaRecuperada = (Regla) entityManager().createQuery("from regla").getSingleResult();
    	reglaRecuperada.chequearCondicionesYEjecutar();
    	CondicionPorMenor menorA30 = new CondicionPorMenor(15,"Temperatura");
    	menorA30.asociarA(reglaRecuperada);
    	reglaRecuperada.agregarCondicion(menorA30);
    	entityManager().persist(reglaRecuperada);
    	Regla reglaRecuperadaV2 = (Regla) entityManager().createQuery("from regla").getSingleResult();
    	assertTrue(reglaRecuperadaV2.getCondicionesACumplir().contains(menorA30));
    }
}
