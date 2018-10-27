package test.database;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoInteligente;
import dominio.manager.DispositivosManager;
import dominio.usuario.Administrador;
import org.junit.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.time.LocalDate;

public class testDispositivoABM extends AbstractPersistenceTest implements WithGlobalEntityManager {
    Administrador admin;

    @Before
    public void setUp() {
        admin = new Administrador("admin", "adminADMIN", LocalDate.now(),"jorge","jorgito");
    }

    @Test
    public void testEliminarDispositivoDeLaBD() {
        DispositivoInteligente disp = entityManager().find(DispositivoInteligente.class,new Long(24));
        entityManager().remove(disp);
        Assert.assertEquals(null, entityManager().find(Dispositivo.class, new Long(24)));
    }

    @Test
    public void testAgregarDispositivoALaBD() {
        DispositivoInteligente nuevoDisp = new DispositivoInteligente.DispositivoInteligenteBuilder("nuevoDisp").build();

        entityManager().persist(nuevoDisp);
        DispositivoInteligente disp = entityManager().createQuery("from DispositivoInteligente di where di.nombre = 'nuevoDisp'", DispositivoInteligente.class).getSingleResult();
        Assert.assertEquals("nuevoDisp", disp.getNombre());
    }

    @Test
    public void testModificarDispositivoDeLaBD() {
        DispositivoInteligente dispNuevo = DispositivosManager.getInstance().traerCiertoDispositivoInteligenteDeLaDB(new Long(9));
        dispNuevo.setNombre("equipoModificado");
        entityManager().persist(dispNuevo);
        Assert.assertEquals("equipoModificado",DispositivosManager.getInstance().traerCiertoDispositivoInteligenteDeLaDB(new Long(9)).getNombre());
    }

    @After
    public void rollback() {
        entityManager().getTransaction().rollback();
    }
}
