package test.database;

import dominio.actuador.Actuador;
import dominio.actuador.OrdenEncenderDI;
import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.manager.ClienteManager;
import dominio.regla.Regla;
import dominio.repositories.RepositorioDispositivo;
import dominio.sensor.Condicion;
import dominio.sensor.CondicionPorIgual;
import dominio.sensor.CondicionPorMayor;
import dominio.sensor.CondicionPorMenor;
import dominio.usuario.*;
import dominio.zonageografica.Ubicacion;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        entityManager().persist(mismoCliente);
        //Lo mismo con el assert
        assertEquals(mismoCliente.getApellido(),ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario("nombreApellido").getApellido());
        assertEquals(mismoCliente.getUsuario(),ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario("nombreApellido").getUsuario());
        assertEquals(mismoCliente.getContrasenia(),ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario("nombreApellido").getContrasenia());
        assertEquals(nuevaUbicacion.getPosicionX(),ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario("nombreApellido").getUbicacion().getPosicionX());
        assertEquals(nuevaUbicacion.getPosicionY(),ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario("nombreApellido").getUbicacion().getPosicionY());
        //
        assertEquals(unCliente.getApellido(),mismoCliente.getApellido());
        assertEquals(unCliente.getUsuario(),mismoCliente.getUsuario());
        assertEquals(unCliente.getContrasenia(),mismoCliente.getContrasenia());
  
        assertTrue(unCliente.getUbicacion().equals(mismoCliente.getUbicacion()));
    }

    @Test
    public void testTraerDispositivoDeLaBDModificarElNombreYGrabarloCASOPRUEBA2() {
    	
    	Intervalo i1 = new Intervalo(LocalDateTime.of(2018,10,12,19,30),LocalDateTime.of(2018,10,12,21,45));
    	Intervalo i2 = new Intervalo(LocalDateTime.of(2018,10,15,11,50),LocalDateTime.of(2018,10,15,13,00));
    	Intervalo i3 = new Intervalo(LocalDateTime.of(2018,11,9,04,20),LocalDateTime.of(2018,11,10,00,10));
    	
    	List<Intervalo> intervalosDeUso = new ArrayList<>();
    	intervalosDeUso.add(i1);
    	intervalosDeUso.add(i2);
    	persist(i1);
        persist(i2);
    	
    	DispositivoInteligente di = new DispositivoInteligente.DispositivoInteligenteBuilder("di").consumoEstimadoPorHora((double)200).intervalosDeUso(intervalosDeUso).build();
    	entityManager().persist(di);

        DispositivoInteligente diRecuperado = (DispositivoInteligente) entityManager().createQuery("from dispositivointeligente where nombre = 'di'").getSingleResult();
        long idQuery = (long) entityManager().createNativeQuery("select idDispositivo from dispositivointeligente where nombre = 'di'").getSingleResult();
        
        
        assertTrue(diRecuperado.getIntervalosDeUso().stream().anyMatch(i-> i.equals(i1)));
        assertTrue(diRecuperado.getIntervalosDeUso().stream().anyMatch(i-> i.equals(i2)));
        assertFalse(diRecuperado.getIntervalosDeUso().stream().anyMatch(i-> i.equals(i3))); 

        diRecuperado.setNombre("nombreModificado");
        entityManager().persist(diRecuperado);
        
        assertEquals("nombreModificado", ((DispositivoInteligente) entityManager().createQuery("from dispositivointeligente where idDispositivo = :id").setParameter("id",idQuery)).getNombre());
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
