package test.database;

import dominio.cargarCliente.CargarTransformadores;
import dominio.cargarCliente.ClienteManager;
import org.junit.Ignore;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;

public class testTransformadores {

    @Ignore
    public void testCargarCliente() {
        CargarTransformadores cargarTransformadores = new CargarTransformadores();
        cargarTransformadores.persistirTransformadores();
        EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    }
}
