package test.database;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.usuario.*;
import dominio.zonageografica.Ubicacion;
import persistence.ClienteManager;
import persistence.DispositivosManager;
import datos.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CasosDePrueba1y2 extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Before
    public void setup() {
        entityManager().getTransaction().begin();
    }

    /*Clases:   Domicilio
                ID
                Cliente
                Ubicacion
    */

    //Metodos: SetUbicacion de cliente

    @Test
    public void casoDePrueba1() {

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

        Ubicacion nuevaUbicacion = new Ubicacion(2, 2);
        mismoCliente.setUbicacion(nuevaUbicacion);
        entityManager().persist(mismoCliente);

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

    /*  Clases:
            Intervalo
            DispositivoInteligente
     */
    @Test
    public void casoDePrueba2() {
    	
    	Intervalo i1 = new Intervalo(LocalDateTime.of(2018,10,12,19,30),LocalDateTime.of(2018,10,12,21,45));
    	Intervalo i2 = new Intervalo(LocalDateTime.of(2018,10,15,11,50),LocalDateTime.of(2018,10,15,13,00));
    	Intervalo i3 = new Intervalo(LocalDateTime.of(2018,11,9,04,20),LocalDateTime.of(2018,11,10,00,10));
    	
    	List<Intervalo> intervalosDeUso = new ArrayList<>();
    	intervalosDeUso.add(i1);
    	intervalosDeUso.add(i2);
    	entityManager().persist(i1);
        entityManager().persist(i2);
    	
    	DispositivoInteligente di = new DispositivoInteligente.DispositivoInteligenteBuilder("di").consumoEstimadoPorHora((double)200).intervalosDeUso(intervalosDeUso).build();
    	entityManager().persist(di);

        DispositivoInteligente diRecuperado = (DispositivoInteligente) entityManager().createNativeQuery("SELECT * FROM dispositivointeligente WHERE nombre = 'di'", DispositivoInteligente.class).getSingleResult();
        BigInteger idQuery = (BigInteger) entityManager().createNativeQuery("select idDispositivo from dispositivointeligente where nombre = 'di'").getSingleResult();
        
        long id = idQuery.longValue();
        
        assertTrue(diRecuperado.getIntervalosDeUso().stream().anyMatch(i-> i.equals(i1)));
        assertTrue(diRecuperado.getIntervalosDeUso().stream().anyMatch(i-> i.equals(i2)));
        assertFalse(diRecuperado.getIntervalosDeUso().stream().anyMatch(i-> i.equals(i3))); 

        diRecuperado.setNombre("nombreModificado");
        entityManager().persist(diRecuperado);
        
        assertEquals("nombreModificado", DispositivosManager.getInstance().getDispositivoInteligenteDeLaBDPorID(id).getNombre());
    }

    /*Clases: VerificarUsuario
              Cliente
    */
    @Test
    public void testTraerClientePorUsuario()
    {
        assertEquals(true,VerificarUsuario.verificar("galvanariel","password"));
    }

    /*Clases: DispositivoInteligente
              Cliente
    */
    @Test
    public void testAgregarDispInteligenteAUnUsuarioCantidadDispositivosInteligentes()
    {

        DispositivoInteligente dispositivoInteligente = RepositorioDispositivo.getInstance().traerDispositivoInteligenteDeNombreConcreto("Aire Acondicionado","De 2200 frigorias");

        Cliente cliente = ClienteManager.getInstance().buscarClienteDeLaBDPorUsuario("galvanariel");

        cliente.agregarDispositivoInteligente(dispositivoInteligente);
        entityManager().persist(dispositivoInteligente);
        Assert.assertEquals(3,cliente.getDispositivosInteligentes().size());
    }
    
    @After
    public void rollback() {
        entityManager().getTransaction().rollback();
    }

}
