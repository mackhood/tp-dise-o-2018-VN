package test.database;

import com.google.gson.Gson;
import dominio.manager.CargarTransformadores;
import dominio.repositories.RepositorioTransformadores;
import dominio.transformador.Transformador;
import dominio.zonageografica.Ubicacion;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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
            List<Transformador> transformadorList =RepositorioTransformadores.getInstance().obtenerTransformadores();;
            transformadorList.stream().forEach(transformador -> entityManager().persist(transformador.getUbicacion()));
            transformadorList.stream().forEach(transformador -> entityManager().persist(transformador));
            entityManager().getTransaction().commit();
        });
        List<Transformador> obtenerListaTransformadores  = entityManager().createQuery("FROM Transformador", Transformador.class).getResultList();

        Transformador nuevoTransformador =new Transformador( );
        nuevoTransformador.setUbicacion(new Ubicacion(10,10));

        RepositorioTransformadores.getInstance().nuevoTransformador(nuevoTransformador);
        //FileReader file = new FileReader(getClass().getClassLoader().getResource("Transformadores.json").getFile());
        //BufferedReader bufferedReader = new BufferedReader(file);

        //gson.toJson(nuevoTransformador, new FileWriter("D:\\file.json"));

    }
}
