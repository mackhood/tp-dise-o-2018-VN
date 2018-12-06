package test.database;

import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.manager.ClienteManager;
import dominio.manager.DispositivosManager;
import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import servicio.ServicioDeInicializacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class cargarDispositivosYClienteDePrueba  extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Test
    public void cargarTablaDispositivos() {
        DispositivosManager.getInstance().persistirDispositivosDelRepositorio();
    }

    @Test
    public void testCargarCliente() {
        ClienteManager.getInstance().persistirClienteDePrueba();
    }


}
