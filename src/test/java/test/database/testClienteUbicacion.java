package test.database;

import dominio.cargarCliente.ClienteManager;
import dominio.usuario.Cliente;
import dominio.zonageografica.Ubicacion;
import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import static junit.framework.TestCase.assertEquals;

public class testClienteUbicacion  extends AbstractPersistenceTest implements WithGlobalEntityManager {
    private Ubicacion nuevaUbicacion = new Ubicacion(2,2);


    @Test
    public void testNuevaUbicacion() {
        Cliente unCliente = entityManager().find(Cliente.class, (new Long(1)));
        assertEquals(nuevaUbicacion.getPosicionX(), unCliente.getUbicacion().getPosicionX());
        assertEquals(nuevaUbicacion.getPosicionY(), unCliente.getUbicacion().getPosicionY());
    }

    @Test
    public void testRecuperarClienteYModificarUbicacion() {
        ClienteManager clienteManager = new ClienteManager();
        clienteManager.modificarUbicacion(new Long(1), nuevaUbicacion);
    }
}
