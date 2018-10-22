package test.database;

import dominio.manager.CargarTransformadores;
import dominio.repositories.RepositorioTransformadores;
import dominio.transformador.Transformador;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class testTransformadores extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Before
    public void setup() {
        entityManager().getTransaction().begin();
    }

    @Test
    public void testCargarTransformador() {
        //CargarTransformadores cargarTransformadores = new CargarTransformadores();
        //cargarTransformadores.persistirTransformadores();
        //EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
        //assertEquals(3,transformadorList.get(0).getUbicacion().getPosicionX());
        withTransaction(() -> {
            List<Transformador> transformadorList =RepositorioTransformadores.getInstance().obtenerTransformadores();;
            transformadorList.stream().forEach(transformador -> entityManager().persist(transformador.getUbicacion()));
            transformadorList.stream().forEach(transformador -> entityManager().persist(transformador));
            entityManager().getTransaction().commit();
        });
       // Transformador transformadorBuscado = entityManager().createQuery("from Transformadores t INNER JOIN Ubicacion u ON t.ubicacion_id=u.id where u.PosicionX = 1",Transformador.class).getSingleResult();

    }
}
