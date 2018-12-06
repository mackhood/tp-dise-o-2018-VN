package reportes;

import dominio.dispositivo.Intervalo;
import dominio.transformador.Transformador;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class ReporteConsumoPorTransformador implements WithGlobalEntityManager {


    public double consumoPorTransformador(long idTransformador, Intervalo i) {

        Transformador t = (Transformador) entityManager().createQuery("from Transformador where id = :id", Transformador.class).setParameter("id", idTransformador).getSingleResult();
        return t.consumoPromedioEnIntervalo(i);
    }
}
