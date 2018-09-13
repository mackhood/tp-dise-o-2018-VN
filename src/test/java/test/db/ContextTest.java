package test.db;

import static org.junit.Assert.*;

import dominio.cargarCliente.CargarCliente;
import dominio.usuario.Cliente;
import org.junit.Assert;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.export.JpaSchemaExport;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ContextTest extends AbstractPersistenceTest implements WithGlobalEntityManager {



    @Test
    public void contextUp() {
        assertNotNull(entityManager());
    }

    @Test
    public void contextUpWithTransaction() throws Exception {
        withTransaction(() -> {});
    }

    @Test
    public void testCargarAlumno()
    {
        CargarCliente cargarCliente = new CargarCliente();
        cargarCliente.persistirCliente();
        //cargarCliente.persistirDispositivos();
        //entityManager().clear();
        EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
    }

    @Test
    public void testRecuperarAlumno()
    {

        Cliente unCliente = entityManager().find(Cliente.class,1);
        Assert.assertEquals('A',unCliente.getNombre());
    }

}