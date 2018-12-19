package test.managers;

import dominio.categoria.Categoria;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import persistence.ClienteManager;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static sun.plugin2.os.windows.OSVERSIONINFOA.size;


public class testClienteManager {
 private  ClienteManager instance;
 private ClienteManager mockClienteManager ;
private Cliente mockCliente;
private Domicilio mockDomicilio;
private ID mockId;
private DispositivoEstandar mockDispositivoEstandar;


    @Before
    public void setUp() {

        mockDomicilio = Mockito.spy( new Domicilio("Av. Cordoba", 1230, 7, 'A'));
        mockId = Mockito.spy( new ID(TiposId.DNI, "40241506"));
        mockClienteManager = Mockito.spy( ClienteManager .getInstance());



        mockCliente = Mockito.spy( new Cliente("Ariel", "Galvan", "galvanariel", "password", mockId, mockDomicilio, 47581269,
                null, null));


        mockDispositivoEstandar = Mockito.spy(  new DispositivoEstandar.DispositivoEstandarBuilder("a1").consumoEstimadoPorHora((double) 300).build());

        List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();

       List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();






    }



    @Test
public void testGetInstance() {

        ClienteManager instance = ClienteManager.getInstance();


        assertEquals(instance.getClass(),ClienteManager.class);

    }


    @Test
    public void esClienteVerdadero () {

        ClienteManager instance = ClienteManager.getInstance();

        assertEquals(true,instance.esCliente("galvanariel"));


    }

    @Test
    public void esClienteFalso () {
        ClienteManager instance = ClienteManager.getInstance();


        assertEquals(false,instance.esCliente("xxxxxx"));


    }


    @Test
    public void testBuscarClienteDeLaBDPorUsuario () {

        ClienteManager instance = ClienteManager.getInstance();

        assertEquals(Cliente.class,instance.buscarClienteDeLaBDPorUsuario("galvanariel").getClass());


    }



/*
    @Test
    public void testGetIdCliente () {
        ClienteManager instance = ClienteManager.getInstance();


        assertEquals(mockCliente.getId().getClass(),instance.getIdDelClientePorUsuario(mockCliente.getUsuario()).getClass());


    }
*/


    @Test
    public void testOtenerPrimerosNClientes () {
        ClienteManager instance = ClienteManager.getInstance();


        assertEquals(1,instance.obtenerPrimerosNClientes(6).size());

    }


/*
    @Test
    public void testConsumoUltimoPeriodo () {

        ClienteManager instance = ClienteManager.getInstance();

        assertEquals(mockCliente.getConsumoUltimoIntervalo(),instance.consumoUltimoPeriodo(mockCliente.getUsuario()));



    }*/


    @Test
    public void getClientesDeLaBD () {
        ClienteManager instance = ClienteManager.getInstance();


    List<Cliente> clientes =mockClienteManager.getClientesDeLaBD();
        assertEquals(clientes,instance.getClientesDeLaBD());



    }
    @Test

    public void testFiltrarClientes () {
        ClienteManager instance = ClienteManager.getInstance();


        List<Cliente> clientes = new ArrayList<>();
        assertEquals(clientes,instance.filtradoClientes("Ariel","Galvan",mockDomicilio.calle));



    }

    @Test
    public void testFiltrarClientesPorApellido () {

        ClienteManager instance = ClienteManager.getInstance();
        List<Cliente> clientes = new ArrayList<>();
        clientes= mockClienteManager.filtrarClientesPorApellido("Galvan");
        assertEquals(clientes,instance.filtrarClientesPorApellido("Galvan"));



    }






}
