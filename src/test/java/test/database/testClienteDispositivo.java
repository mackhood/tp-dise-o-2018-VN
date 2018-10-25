package test.database;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.manager.ClienteManager;
import dominio.usuario.*;
import dominio.zonageografica.Ubicacion;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

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
        mismoCliente.setNombre("nombreNuevo");
        Ubicacion nuevaUbicacion = new Ubicacion(2, 2);
        mismoCliente.setUbicacion(nuevaUbicacion);
        entityManager().persist(unCliente);
        //Lo mismo con el assert
        assertEquals(nuevaUbicacion.getPosicionX(),entityManager().createQuery("from Cliente c where nombre = 'nombreNuevo'", Cliente.class).getSingleResult().getUbicacion() .getPosicionX());
    }

    @Test
    public void testTraerDispositivoDeLaBDModificarElNombreYGrabarloCASOPRUEBA2() {
        Dispositivo dispositivo = entityManager().find(DispositivoInteligente.class, new Long(13));
        dispositivo.setNombre("nombreModificado");
        //FALTA MOSTRAR POR CONSOLA TODOS LOS INTERVALOS DONDE ESTUVO ENCENDIDO EL DISPOSITIVO
        assertEquals("nombreModificado", entityManager().find(DispositivoInteligente.class, new Long(13)).getNombre());
    }
    @Test
    public void testTraerClientePorUsuario()
    {
        //VerificarUsuario verificarUsuario = new VerificarUsuario();
        assertEquals(true,VerificarUsuario.verificar("galvanariel","password"));
    }

    @After
    public void rollback() {
        entityManager().getTransaction().rollback();
    }

}
