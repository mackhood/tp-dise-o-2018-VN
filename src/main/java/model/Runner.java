package model;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import javax.persistence.EntityManager;
import java.util.List;


public class Runner {
	public static void main(String[] args) {
		new Fixture().run();
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		List<Alumno> alumnos = entityManager.createQuery("" + "from Alumno "+ "where " + "nombre='jugo'").getResultList();

		for (Alumno alumno : alumnos){
			System.out.println(alumno.getNombre());
		}

		/*EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		//entityManager.find(Alumno.class,)
		*/
	}
}
