package test.database;

import static org.junit.Assert.*;

import dominio.cargarCliente.ClienteManager;
import dominio.usuario.Cliente;
import org.junit.Assert;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {


    @Test
    public void contextUp() {
        assertNotNull(entityManager());
    }

    @Test
    public void contextUpWithTransaction() throws Exception {
        withTransaction(() -> {
        });
    }

    @Test
    public void testCargarAlumno() {
        ClienteManager clienteManager = new ClienteManager();
        clienteManager.persistirCliente();
        //clienteManager.persistirDispositivos();
        //entityManager().clear();
        EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    }



}