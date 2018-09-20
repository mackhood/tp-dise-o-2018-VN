package test.database;

import dominio.manager.ClienteManager;
import dominio.usuario.Cliente;
import dominio.zonageografica.Ubicacion;
import org.junit.Ignore;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import static junit.framework.TestCase.assertEquals;

public class testClienteUbicacion  extends AbstractPersistenceTest implements WithGlobalEntityManager {
    private Ubicacion nuevaUbicacion = new Ubicacion(2,2);


    @Ignore
    public void testNuevaUbicacion() {
        Cliente unCliente = entityManager().find(Cliente.class, (new Long(1)));
        assertEquals(nuevaUbicacion.getPosicionX(), unCliente.getUbicacion().getPosicionX());
        assertEquals(nuevaUbicacion.getPosicionY(), unCliente.getUbicacion().getPosicionY());
    }

    @Ignore
    public void testRecuperarClienteYModificarUbicacion() {
    }
}
