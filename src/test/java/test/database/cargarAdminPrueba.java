package test.database;


import dominio.manager.AdministradorManager;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

public class cargarAdminPrueba extends AbstractPersistenceTest implements WithGlobalEntityManager {


    @Test
    public void testCargarCliente() {
        AdministradorManager.getInstance().persistirAdminDePrueba();


    }

}








