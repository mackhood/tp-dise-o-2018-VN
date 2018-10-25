package test.database;

import dominio.manager.ClienteManager;
import dominio.manager.DispositivosPersistirManager;
import org.junit.Test;

public class cargarDispositivosYClienteDePrueba {
    @Test
    public void cargarTablaDispositivos() {
        DispositivosPersistirManager.getInstance().persistirDispositivosDelRepositorio();
    }

    @Test
    public void testCargarCliente() {
        ClienteManager.getInstance().persistirClienteDePrueba();
    }
}
