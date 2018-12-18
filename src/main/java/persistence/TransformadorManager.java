package persistence;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.dispositivo.Intervalo;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.zonageografica.Ubicacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import javax.persistence.Query;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransformadorManager implements WithGlobalEntityManager, TransactionalOps {

	private static TransformadorManager instance = new TransformadorManager();

	private TransformadorManager() {
	}

	public static TransformadorManager getInstance() {
		return instance;
	}

	public void transformadoresNoPersistidosYPersistirlos(List<Transformador> transformadors) {
		transformadors.stream().forEach(transformador -> {
			int idTransformador = transformador.getIdTransformador();
			Query query = entityManager().createQuery(
					"from Transformador t where idTransformador='" + idTransformador + "'", Transformador.class);
			query.setMaxResults(1);
			List<Transformador> transformadorObtenidos = query.getResultList();

			if (transformadorObtenidos.size() == 0) {
				entityManager().persist(transformador.getUbicacion());
				entityManager().persist(transformador);
			}
		});
		entityManager().getTransaction().commit();
	}

	public Transformador obtenerTrasformador(int idTrasnformador) {
		Query query = entityManager().createQuery(
				"from Transformador t where idTransformador='" + idTrasnformador + "'", Transformador.class);
		query.setMaxResults(1);
		Transformador transformadorObtenidos = (Transformador) query.getSingleResult();
		return transformadorObtenidos;
	}

	public long obtenerIdBD(int numSerie) {

		BigInteger id = (BigInteger) entityManager()
				.createNativeQuery("SELECT id FROM transformador WHERE idTransformador = :numSerie")
				.setParameter("numSerie", numSerie).getSingleResult();
		
		return id.longValue();
	}

}
