package reportes;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import dominio.dispositivo.Intervalo;
import dominio.transformador.Transformador;

public class ReporteConsumoPorTransformador implements WithGlobalEntityManager {

	
	public double consumoPorTransformador(long idTransformador, Intervalo i) {
		
		Transformador t = (Transformador) entityManager().createQuery("from Transformador where id = :id").setParameter("id", idTransformador);
		return t.consumoEnIntervalo(i);
	}
}
