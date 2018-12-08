package dominio.manager;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.zonageografica.Ubicacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import javax.persistence.Query;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransformadorManager implements WithGlobalEntityManager, TransactionalOps {

    private static TransformadorManager instance = new TransformadorManager();

    private TransformadorManager() {
    }

    public static TransformadorManager getInstance() {
        return instance;
    }

    public void persistirTransformadorDePrueba() {

        Intervalo i1 = new Intervalo(LocalDateTime.of(2018, 10, 12, 13, 00), LocalDateTime.of(2018, 10, 12, 22, 45));
        Intervalo i2 = new Intervalo(LocalDateTime.of(2018, 10, 11, 23, 19), LocalDateTime.of(2018, 10, 12, 04, 20));
        Intervalo i3 = new Intervalo(LocalDateTime.of(2018, 10, 12, 07, 30), LocalDateTime.of(2018, 10, 12, 9, 20));
        List<Intervalo> li = new ArrayList<>();
        li.add(i1);
        li.add(i2);
        li.add(i3);
        DispositivoInteligente di1 = new DispositivoInteligente.DispositivoInteligenteBuilder("di1")
                .consumoEstimadoPorHora((double) 200).intervalosDeUso(li).build();
        di1.agregarListaIntervalos(li);
        entityManager().persist(di1);

        DispositivoInteligente di2 = new DispositivoInteligente.DispositivoInteligenteBuilder("di2")
                .consumoEstimadoPorHora((double) 500).intervalosDeUso(li).build();
        di2.agregarListaIntervalos(li);
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

    public void transformadoresNoPersistidosYPersistirlos(List<Transformador> transformadors) {
        transformadors.stream().forEach(transformador -> {
            int idTransformador = transformador.getIdTransformador();
            Query query = entityManager().createQuery(
                    "from Transformador t where idTransformador='" + idTransformador + "'", Transformador.class);
            query.setMaxResults(1);
            List<Transformador> transformadorObtenidos = query.getResultList();
            if (transformadorObtenidos.size() == 0) {
                entityManager().persist(transformador.getUbicacion());
                entityManager().persist(transformador);
            }
        });
        entityManager().getTransaction().commit();
    }

    public Transformador obtenerTrasformador(int idTrasnformador) {
        Query query = entityManager().createQuery(
                "from Transformador t where idTransformador='" + idTrasnformador + "'", Transformador.class);
        query.setMaxResults(1);
        Transformador transformadorObtenidos = (Transformador) query.getSingleResult();
        return transformadorObtenidos;
    }

    public long obtenerIdBD(int numSerie) {

        BigInteger id = (BigInteger) entityManager()
                .createNativeQuery("SELECT id FROM transformador WHERE idTransformador = :numSerie")
                .setParameter("numSerie", numSerie).getSingleResult();

        return id.longValue();
    }


}
