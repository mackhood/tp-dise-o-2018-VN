package test.db;

import static org.junit.Assert.*;

import dominio.cargarCliente.ClienteManager;
import dominio.dispositivo.Dispositivo;
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

    @Test
    public void testRecuperarAlumnoConId1YCompararSiSuDispositivoInteligente1EsElAireAcondicionado() {
        Cliente unCliente = entityManager().find(Cliente.class, (new Long(1)));
        //System.out.println(unCliente.getDispositivosInteligentes().get(0).getNombre());
        //System.out.println(unCliente.getTodosLosDispositivos().get(1).getNombre());
        Assert.assertEquals("aireAcondicionado", unCliente.getDispositivosInteligentes().get(0).getNombre());
    }

    @Test
    public void testRecuperarAlumnoConId1YModificarNombreDispositivoInteligente1() {
        ClienteManager clienteManager = new ClienteManager();
        clienteManager.modificarNombreDeUnDispositivoDelCliente(new Long(1),"ddd","aireAcondicionadoModificado");
    }

}