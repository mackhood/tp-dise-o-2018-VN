package test;

import Clases.Administrador;
import Clases.Cliente;
import Clases.Dispositivo;
import Clases.entities.ProcessingDataFailedException;
import Clases.repositories.RepositorioAdministradores;
import Clases.repositories.RepositorioClientes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class testCliente {
    Cliente unaPersona;
    Cliente otraPersona;
    Dispositivo heladera;
    Dispositivo microondas;
    Dispositivo lavaropas;
    List<Dispositivo> dispositivos;
    Administrador admin;
    Administrador admin2;
    List<Cliente> clientes;
    List<Administrador> admins;

    @Before
    public void init() throws ProcessingDataFailedException {

        RepositorioClientes repositorioClientes = RepositorioClientes.instance;
        RepositorioAdministradores repositorioAdministradores = RepositorioAdministradores.instance;

        clientes = repositorioClientes.obtenerClientes();
        unaPersona = clientes.get(0);
        otraPersona = clientes.get(1);

        admins = repositorioAdministradores.obtenerAdministradores();
        admin = admins.get(0);
        admin2 = admins.get(1);
    }

    @Test
    public void testCantMesesDelAdminCaso1() throws ProcessingDataFailedException {

        this.init();
        Assert.assertEquals(23, admin.cantMesesComoAdmin());
    }

    @Test
    public void testCantMesesDelAdminCaso2() throws ProcessingDataFailedException {

        this.init();
        Assert.assertEquals(43, admin2.cantMesesComoAdmin());
    }

    @Test
    public void testFacturacionEstimadaR8() throws ProcessingDataFailedException {

        this.init();
        Dispositivo a1 = new Dispositivo("a1", 300, false);
        Dispositivo a2 = new Dispositivo("a2", 500, false);
        Dispositivo a3 = new Dispositivo("a3", 100, false);
        unaPersona.agregarDispositivo(a1);
        unaPersona.agregarDispositivo(a2);
        unaPersona.agregarDispositivo(a3);
        unaPersona.usarDispositivo(a1, LocalDateTime.of(2018, 03, 02, 02, 30), 3);
        unaPersona.usarDispositivo(a3, LocalDateTime.of(2018, 05, 12, 23, 40), 2);
        //1100*0.851 +545.96 = 1482.06 //
        assertEquals("El valor de la facturación (categoría R8), no se condice con el valor esperado", 1482.06, unaPersona.obtenerGastosAproximados(), 0);
    }

    @Test
    public void testFacturacionEstimadaR2() throws ProcessingDataFailedException {

        this.init();
        Dispositivo a1 = new Dispositivo("a1", 210, false);
        unaPersona.agregarDispositivo(a1);
        unaPersona.usarDispositivo(a1, LocalDateTime.of(2018, 01, 11, 21, 15), 1);
        // 210*0.644+35.32 = 170.56 //
        Assert.assertEquals("El valor de la facturación (categoría R2), no se condice con el valor esperado", 170.56, unaPersona.obtenerGastosAproximados(), 0);
    }

    @Test
    public void testSaberSiAlgunDispositivoEstaEncendido() throws ProcessingDataFailedException {

        this.init();

        assertFalse(unaPersona.algunDispositivoEncendido());
    }

    @Test
    public void testSaberCantidadDeDispositivosEncendidos() throws ProcessingDataFailedException {

        this.init();
        assertEquals(0, unaPersona.cantidadDeDispositivosEncendidos());
    }

    @Test
    public void testCantidadTotalDispositivos() throws ProcessingDataFailedException {

        this.init();
        assertEquals(3, unaPersona.cantidadDeDispositivos());
    }

    @Test
    public void testCantidadDispositivosApagados() throws ProcessingDataFailedException {

        this.init();
        assertEquals(3, unaPersona.cantidadDeDispositivosApagados());
    }

    @Test
    public void consumoEnergetico() throws ProcessingDataFailedException {

        this.init();
        Dispositivo d = new Dispositivo("d", 215, false);
        otraPersona.agregarDispositivo(d);
        otraPersona.usarDispositivo(d, LocalDateTime.of(2017, 11, 28, 00, 10), 3);
        // 215*3 = 645 //
        assertEquals("El valor de consumo energético no coincide con lo esperado", 645, otraPersona.consumoEnergeticoTotal(), 0);
    }
}
