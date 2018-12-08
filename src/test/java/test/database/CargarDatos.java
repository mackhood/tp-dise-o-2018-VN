package test.database;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.manager.AdministradorManager;
import dominio.manager.ClienteManager;
import dominio.manager.DispositivosManager;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.zonageografica.Ubicacion;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CargarDatos extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Test
    public void testCargarDatos() {
        AdministradorManager.getInstance().persistirAdminDePrueba();
    }

    @Test
    public void cargarTablaDispositivos() {
        DispositivosManager.getInstance().persistirDispositivosDelRepositorio();
    }

    @Test
    public void testCargarCliente() {
        ClienteManager.getInstance().persistirClienteDePrueba();
    }

    @Test
    public void cargarIntervaloADispositivoID13() {
        String nombreDI = "diFer";
        DispositivoInteligente diPersistir = new DispositivoInteligente.DispositivoInteligenteBuilder(nombreDI).consumoEstimadoPorHora((double) 200).build();
        persist(diPersistir);

        DispositivoInteligente dispositivoInteligenteObtenido = (DispositivoInteligente) entityManager().createQuery("from DispositivoInteligente where nombre = :nombre", DispositivoInteligente.class).setParameter("nombre", nombreDI).getSingleResult();
        LocalDateTime horaEncendido = LocalDateTime.of(2018, 6, 8, 15, 30, 30, 100);
        LocalDateTime horaApagado = LocalDateTime.of(2018, 6, 8, 21, 25, 30, 100);
        Intervalo intervalo = new Intervalo(horaEncendido, horaApagado);
        List<Intervalo> intervalos = new ArrayList<>();
        intervalos.add(intervalo);
        persist(intervalo);
        dispositivoInteligenteObtenido.agregarListaIntervalos(intervalos);
        entityManager().getTransaction().commit();

    }

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
