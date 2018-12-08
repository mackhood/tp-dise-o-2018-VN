package test.database;

import dominio.manager.ClienteManager;
import dominio.manager.DispositivosManager;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class cargarDispositivosYClienteDePrueba extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Test
    public void cargarTablaDispositivos() {
        DispositivosManager.getInstance().persistirDispositivosDelRepositorio();
    }

    @Test
    public void testCargarCliente() {
        ClienteManager.getInstance().persistirClienteDePrueba();
    }


}
