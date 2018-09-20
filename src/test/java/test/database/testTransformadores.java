package test.database;

import dominio.manager.CargarTransformadores;
import org.junit.Ignore;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;

public class testTransformadores {

    @Ignore
    public void testCargarTransformador() {
        CargarTransformadores cargarTransformadores = new CargarTransformadores();
        cargarTransformadores.persistirTransformadores();
        EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    }
}
