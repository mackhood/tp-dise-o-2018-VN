package test.database;

import dominio.repositories.RepositorioTransformadores;
import dominio.transformador.Transformador;
import dominio.zonageografica.Ubicacion;
import persistence.TransformadorManager;

import org.junit.Before;
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

    //El problema con este test es q corre bien la primera vez
    //pero cuando crea el archivo transformadorTest.json , la segunda vez
    //lee este archivo y no el q esta en resources en test.
    //Clases: Transformadores
    //        Ubicacion
    //        Gson
    //Metodo Importante: TransformadorManager.getInstance().transformadoresNoPersistidosYPersistirlos(ListaDeTransformadores)
    //Evita q persista repetidos
    @Test
    public void testPersistirTransformadoresYconsultarCantidad() throws IOException {
        String archivo = "transformadorTest.json";
        TransformadorManager.getInstance().transformadoresNoPersistidosYPersistirlos(RepositorioTransformadores.getInstance().obtenerTransformadores(archivo));
        List<Transformador> obtenerListaTransformadores = entityManager().createQuery("from Transformador", Transformador.class).getResultList();
        assertEquals(3, obtenerListaTransformadores.size());
    }
    //Clases: Transformadores
    //        Ubicacion
    //        Gson
    //        RepositorioTransformadores
    //Metodo Importante: TransformadorManager.getInstance().transformadoresNoPersistidosYPersistirlos(ListaDeTransformadores)
    @Test
    public void testPersistirNuevoTransformadorEnJson() throws IOException {
        Transformador nuevoTransformador = new Transformador(15);
        nuevoTransformador.setUbicacion(new Ubicacion(10, 10));
        String archivo = "transformadorTest.json";
        List <Transformador> transformadorList =RepositorioTransformadores.getInstance().nuevoTransformador(nuevoTransformador,archivo);
        TransformadorManager.getInstance().transformadoresNoPersistidosYPersistirlos(transformadorList);
        List<Transformador> obtenerListaTransformadores = entityManager().createQuery("from Transformador", Transformador.class).getResultList();
        assertEquals(4, obtenerListaTransformadores.size());
    }
}
