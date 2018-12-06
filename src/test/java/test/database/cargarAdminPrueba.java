package test.database;


import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.manager.AdministradorManager;
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

public class cargarAdminPrueba extends AbstractPersistenceTest implements WithGlobalEntityManager {


    @Test
    public void testCargarCliente() {
        AdministradorManager.getInstance().persistirAdminDePrueba();


    }

}








