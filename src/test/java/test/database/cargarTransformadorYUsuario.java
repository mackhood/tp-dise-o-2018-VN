package test.database;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.zonageografica.Ubicacion;

public class cargarTransformadorYUsuario extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	@Test
	public void cargarTransformador() {
	
    Intervalo i1 = new Intervalo(LocalDateTime.of(2018, 10, 12, 13, 10), LocalDateTime.of(2018, 10, 12, 22, 45));
    Intervalo i2 = new Intervalo(LocalDateTime.of(2018, 10, 11, 22, 20), LocalDateTime.of(2018, 10, 12, 04, 20));
    Intervalo i3 = new Intervalo(LocalDateTime.of(2018, 10, 12, 07, 30), LocalDateTime.of(2018, 10, 12, 16, 20));
    Intervalo i4 = new Intervalo(LocalDateTime.of(2018, 10, 12, 13, 10), LocalDateTime.of(2018, 10, 12, 22, 45));
    Intervalo i5 = new Intervalo(LocalDateTime.of(2018, 10, 11, 22, 20), LocalDateTime.of(2018, 10, 12, 04, 20));
    Intervalo i6 = new Intervalo(LocalDateTime.of(2018, 10, 12, 07, 30), LocalDateTime.of(2018, 10, 12, 16, 20));
    List<Intervalo> li = new ArrayList<>();
    List<Intervalo> li2 = new ArrayList<>();
    li.add(i1);
    li.add(i2);
    li.add(i3);
    li2.add(i4);
    li2.add(i5);
    li2.add(i6);
    DispositivoInteligente di1 = new DispositivoInteligente.DispositivoInteligenteBuilder("di1").consumoEstimadoPorHora((double) 200).intervalosDeUso(li).build();
    di1.agregarListaIntervalos(li);
    entityManager().persist(di1);

    DispositivoInteligente di2 = new DispositivoInteligente.DispositivoInteligenteBuilder("di2").consumoEstimadoPorHora((double) 500).intervalosDeUso(li).build();
    di2.agregarListaIntervalos(li2);
    entityManager().persist(di2);

    Domicilio domicilio = new Domicilio("av cordoba", 1234, 7, 'A');
    ID id = new ID(TiposId.DNI, "10125789");
    List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();
    List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();

    dispositivosInteligentes.add(di1);
    dispositivosInteligentes.add(di2);
    Cliente unCliente = new Cliente("fernando", "sierra", "fernandosierra9", "password", id, domicilio, 47581269,
            dispositivosEstandares, dispositivosInteligentes);
   
    Ubicacion ubicacion = new Ubicacion(5, 2);
    unCliente.setUbicacion(ubicacion);
    entityManager().persist(ubicacion);
    entityManager().persist(unCliente);
    Transformador nuevoTransformador = new Transformador(3);
    Ubicacion ubicacionTrasnformador = new Ubicacion(15, 15);
    entityManager().persist(ubicacionTrasnformador);
    nuevoTransformador.setUbicacion(ubicacionTrasnformador);
    nuevoTransformador.agregarCliente(unCliente);
    entityManager().persist(nuevoTransformador);
    
    entityManager().getTransaction().commit();
	}
}
