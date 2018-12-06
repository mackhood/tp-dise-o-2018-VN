package test.database;

import dominio.manager.TransformadorManager;
import dominio.repositories.RepositorioTransformadores;
import dominio.transformador.Transformador;
import dominio.zonageografica.Ubicacion;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CasoDePrueba4 extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Before
    public void setup() {
        entityManager().getTransaction().begin();
    }

    @Test
    public void testPersistirTransformadoresYconsultarCantidad() throws IOException {

        String archivo = "transformadorTest.json";
        List<Transformador> transformadorList =RepositorioTransformadores.getInstance().obtenerTransformadores(archivo);
        assertEquals(2,transformadorList.size());
        withTransaction(() -> {

            transformadorList.stream().forEach(transformador -> entityManager().persist(transformador.getUbicacion()));
            transformadorList.stream().forEach(transformador -> entityManager().persist(transformador));
            entityManager().getTransaction().commit();
        });

        List<Transformador> obtenerListaTransformadores  = entityManager().createQuery("from Transformador", Transformador.class).getResultList();
        assertEquals(2,obtenerListaTransformadores.size());
    }
    @Test
    public void testPersistirNuevoTransformadorEnJson() throws IOException {
        String archivo = "transformadorTest.json";
        List<Transformador> transformadorList =RepositorioTransformadores.getInstance().obtenerTransformadores();
        Transformador nuevoTransformador =new Transformador(2);
        nuevoTransformador.setUbicacion(new Ubicacion(10,10));
        transformadorList.add(nuevoTransformador);
        RepositorioTransformadores.getInstance().nuevoTransformador(transformadorList);
        List<Transformador> transformadorListDesdeJson=RepositorioTransformadores.getInstance().obtenerTransformadores(archivo);
        TransformadorManager.getInstance().transformadoresNoPersistidosYPersistirlos(transformadorListDesdeJson);
        List<Transformador> obtenerListaTransformadores  = entityManager().createQuery("from Transformador", Transformador.class).getResultList();
        assertEquals(3,obtenerListaTransformadores.size());
    }

}
