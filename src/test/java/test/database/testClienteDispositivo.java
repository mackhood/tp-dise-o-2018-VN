package test.database;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.manager.ClienteManager;
import dominio.repositories.RepositorioDispositivo;
import dominio.usuario.*;
import dominio.zonageografica.Ubicacion;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class testClienteDispositivo extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Before
    public void setup() {
        entityManager().getTransaction().begin();
    }

    @Test
    public void testPersistirUsuarioNuevoRecuperarloModificarSuGeolocalizacionYGrabarloEvaluarCASOPRUEBA1() {

        Domicilio domicilio = new Domicilio("av cordoba", 1234, 7, 'A');
        ID id = new ID(TiposId.DNI, "10125789");
        List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();
        List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();

        Cliente unCliente = new Cliente("nombre", "apellido", "nombreApellido","password", id, domicilio, 47581269,
                dispositivosEstandares, dispositivosInteligentes);

        Ubicacion ubicacion = new Ubicacion(5, 2);
        unCliente.setUbicacion(ubicacion);
        entityManager().persist(unCliente);

        Cliente mismoCliente = entityManager().createQuery("from Cliente c where nombre = 'nombre'", Cliente.class).getSingleResult();
        //En vez de modificar su nombre modificar la geolocalizacion
        //mismoCliente.setNombre("nombreNuevo");
        Ubicacion nuevaUbicacion = new Ubicacion(2, 2);
        mismoCliente.setUbicacion(nuevaUbicacion);
        entityManager().persist(unCliente);
        //Lo mismo con el assert
        assertEquals(mismoCliente.getApellido(),ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario("nombreApellido").getApellido());
        assertEquals(mismoCliente.getUsuario(),ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario("nombreApellido").getUsuario());
        assertEquals(mismoCliente.getContrasenia(),ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario("nombreApellido").getContrasenia());
        assertEquals(nuevaUbicacion.getPosicionX(),ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario("nombreApellido").getUbicacion().getPosicionX());
        assertEquals(nuevaUbicacion.getPosicionY(),ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario("nombreApellido").getUbicacion().getPosicionY());
        //assertEquals(nuevaUbicacion.getPosicionX(),entityManager().createQuery("from Cliente c where nombre = 'nombreNuevo' and apellido = 'apellido' and contrasenia = 'password'", Cliente.class).getSingleResult().getUbicacion() .getPosicionX());
    }

    @Test
    public void testTraerDispositivoDeLaBDModificarElNombreYGrabarloCASOPRUEBA2() {

        LocalDateTime horaEncendido = LocalDateTime.of(2018, 6, 8, 15, 30, 30, 100);
        LocalDateTime horaApagado = LocalDateTime.of(2018, 6, 8, 21, 25, 30, 100);
        Intervalo intervalo = new Intervalo(horaEncendido,horaApagado);
        List<Intervalo> intervalos = new ArrayList<>();
        intervalos.add(intervalo);

        DispositivoInteligente di = entityManager().find(DispositivoInteligente.class, new Long(13));
        di.agregarListaIntervalos(intervalos);

        di.setNombre("nombreModificado");
        entityManager().persist(di);
        //FALTA MOSTRAR POR CONSOLA TODOS LOS INTERVALOS DONDE ESTUVO ENCENDIDO EL DISPOSITIVO
        assertEquals("nombreModificado", entityManager().find(DispositivoInteligente.class, new Long(13)).getNombre());
    }
    @Test
    public void testTraerClientePorUsuario()
    {
        //VerificarUsuario verificarUsuario = new VerificarUsuario();
        assertEquals(true,VerificarUsuario.verificar("galvanariel","password"));
    }

    @Test
    public void testAgregarDispInteligenteAUnUsuarioCantidadDispositivosInteligentes()
    {

        DispositivoInteligente dispositivoInteligente = RepositorioDispositivo.getInstance().traerDispositivoInteligenteDeNombreConcreto("aireAcondicionado","De 2200 frigorias");
        Cliente cliente = ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario("galvanariel");
        cliente.agregarDispositivoInteligente(dispositivoInteligente);
        entityManager().persist(dispositivoInteligente);
        Assert.assertEquals(3,cliente.getDispositivosInteligentes().size());
    }

    //@After
    public void rollback() {
        entityManager().getTransaction().rollback();
    }

}
