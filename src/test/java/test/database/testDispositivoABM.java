package test.database;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.repositories.RepositorioDispositivo;
import dominio.usuario.Administrador;
import org.junit.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.time.LocalDate;

public class testDispositivoABM implements WithGlobalEntityManager {
    Administrador admin;

    @Before
    public void setUp(){
        admin = new Administrador("admin","adminADMIN", LocalDate.now());
    }

    @Test
    public void testEliminarDispositivoDeLaBD()
    {
        admin.eliminarDispositivoDB(new Long(24));
        Assert.assertEquals(null,entityManager().find(Dispositivo.class,new Long(24)));
    }
    @Test
    public void testAgregarDispositivoALaBD(){
        DispositivoInteligente nuevoDisp = new DispositivoInteligente.DispositivoInteligenteBuilder("nuevoDisp").build();
        admin.agregarDispositivoDB(nuevoDisp);
        DispositivoInteligente disp = entityManager().createQuery("from DispositivoInteligente di where nombre = 'nuevoDisp'", DispositivoInteligente.class).getSingleResult();
        Assert.assertEquals("nuevoDisp",disp.getNombre());
    }
    //NO FUNCIONA MODIFICAR
    @Ignore
    public void testModificarDispositivoDeLaBD(){
        DispositivoEstandar dispNuevo = RepositorioDispositivo.getInstance().traerDispositivoEstandarDeNombreConcreto("microondas","Convencional");
        admin.editarDispositivoDB(new Long(5),dispNuevo);
    }
    @After
    public void rollback(){
        entityManager().getTransaction().rollback();
    }
}
