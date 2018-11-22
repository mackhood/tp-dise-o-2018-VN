package test.database;

import dominio.repositories.RepositorioTransformadores;
import dominio.transformador.Transformador;
import dominio.zonageografica.Ubicacion;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CasoDePrueba4 extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Before
    public void setup() {
        entityManager().getTransaction().begin();
    }

    @Test
    public void testPersistirTransformadoresYconsultarCantidad() throws IOException {

        withTransaction(() -> {
            List<Transformador> transformadorList =RepositorioTransformadores.getInstance().obtenerTransformadores();
            transformadorList.stream().forEach(transformador -> entityManager().persist(transformador.getUbicacion()));
            transformadorList.stream().forEach(transformador -> entityManager().persist(transformador));
            entityManager().getTransaction().commit();
        });

        List<Transformador> obtenerListaTransformadores  = entityManager().createQuery("from Transformador", Transformador.class).getResultList();

    }
    @Test
    public void testPersistirNuevoTransformadorEnJson() throws IOException {
        List<Transformador> transformadorList =RepositorioTransformadores.getInstance().obtenerTransformadores();
        Transformador nuevoTransformador =new Transformador( );
        nuevoTransformador.setUbicacion(new Ubicacion(10,10));
        transformadorList.add(nuevoTransformador);
        RepositorioTransformadores.getInstance().nuevoTransformador(transformadorList);
    }

}
