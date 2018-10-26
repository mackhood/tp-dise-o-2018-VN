package test.database;

import dominio.manager.ClienteManager;
import dominio.manager.DispositivosManager;
import org.junit.Test;
import servicio.Inicializacion;

public class cargarDispositivosYClienteDePrueba {

    @Test
    public void cargarTablaDispositivos() {
        DispositivosManager.getInstance().persistirDispositivosDelRepositorio();
    }

    @Test
    public void testCargarCliente() {
        ClienteManager.getInstance().persistirClienteDePrueba();
    }
}
