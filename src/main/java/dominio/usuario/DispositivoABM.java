package dominio.usuario;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class DispositivoABM implements WithGlobalEntityManager{
    public void agregarDispositivoBD(Dispositivo unDisp){
        entityManager().getTransaction().begin();
        entityManager().persist(unDisp);
    }

    public void modificarDispositivoBD(Long idDispViejo, DispositivoEstandar dispNuevo){
        DispositivoEstandar dispAModificar = entityManager().find(DispositivoEstandar.class, idDispViejo);
        entityManager().getTransaction().begin();
        dispAModificar = dispNuevo;
        entityManager().merge(dispAModificar);
        entityManager().getTransaction().commit();

    }

    public void eliminarDispositivoInteligenteBD(Long idDispEliminar){
        entityManager().getTransaction().begin();
        Dispositivo dispEliminar = entityManager().find(Dispositivo.class, idDispEliminar);
        entityManager().remove(dispEliminar);
    }
}
