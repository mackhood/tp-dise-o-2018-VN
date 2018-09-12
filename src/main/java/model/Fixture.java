package model;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.Date;


public class Fixture implements WithGlobalEntityManager,TransactionalOps {

public void run() {

    withTransaction(()->{
        Alumno alumno = new Alumno("1234","german","jugo@","mackhood","jugo","1234");
        entityManager().persist(alumno);
        entityManager().persist(new Alumno("1234","fer","jugo@","mackhood","jugo","1234"));
        Tarea tarea= new Tarea(new Date(),"un Enunciado");
        entityManager().persist(tarea);

        Asignacion asignacion = new Asignacion(tarea);

        asignacion.agregarNota("M");
        asignacion.agregarNota("R");
        asignacion.agregarNota("B");

        entityManager().persist(asignacion);
        alumno.getAsignaciones().add(asignacion);
    });
    }
}
