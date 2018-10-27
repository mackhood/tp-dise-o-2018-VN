package test.database;

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

public class testTransformadores extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @Before
    public void setup() {
        entityManager().getTransaction().begin();
    }

    @Test
    public void testPersistirTransformadores() throws IOException {

        withTransaction(() -> {
            List<Transformador> transformadorList =RepositorioTransformadores.getInstance().obtenerTransformadores();
            transformadorList.stream().forEach(transformador -> entityManager().persist(transformador.getUbicacion()));
            transformadorList.stream().forEach(transformador -> entityManager().persist(transformador));
            entityManager().getTransaction().commit();
        });

        List<Transformador> obtenerListaTransformadores  = entityManager().createQuery("from Transformador", Transformador.class).getResultList();

        Transformador nuevoTransformador =new Transformador( );
        nuevoTransformador.setUbicacion(new Ubicacion(10,10));

        RepositorioTransformadores.getInstance().nuevoTransformador(nuevoTransformador);
        //FileReader file = new FileReader(getClass().getClassLoader().getResource("Transformadores.json").getFile());
        //BufferedReader bufferedReader = new BufferedReader(file);

        //gson.toJson(nuevoTransformador, new FileWriter("D:\\file.json"));

    }
}
