package dominio.manager;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.zonageografica.Ubicacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

public class ClienteManager implements WithGlobalEntityManager, TransactionalOps {

    private static ClienteManager instance = new ClienteManager();

    private ClienteManager() {
    }

    public static ClienteManager getInstance() {
        return instance;
    }

    public void persistirClienteDePrueba() {
        withTransaction(() -> {
            Domicilio domicilio = new Domicilio("av cordoba", 1234, 7, 'A');
            ID id = new ID(TiposId.DNI, "10125789");
            DispositivoEstandar dispEstandar = entityManager().find(DispositivoEstandar.class, new Long(1));
            List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();
            dispositivosEstandares.add(dispEstandar);

            DispositivoInteligente aireAcondicionado3500 = entityManager().find(DispositivoInteligente.class, new Long(9));
            DispositivoInteligente ventilador = entityManager().find(DispositivoInteligente.class, new Long(17));
            List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();

            dispositivosInteligentes.add(aireAcondicionado3500);
            dispositivosInteligentes.add(ventilador);

            Cliente unCliente = new Cliente("ariel", "galvan", "galvanariel","password", id, domicilio, 47581269,
                    dispositivosEstandares, dispositivosInteligentes);

            Ubicacion ubicacion = new Ubicacion(5, 2);
            unCliente.setUbicacion(ubicacion);

            entityManager().persist(unCliente);
            entityManager().getTransaction().commit();
        });
    }

    public Cliente buscarClientePorUsuario(String username){

        //System.out.println(username.getClass());
        //System.out.println("from Cliente c where usuario ='"+username+"'");
        Cliente cliente = entityManager().createQuery("from Cliente where usuario='"+username+"'" ,Cliente.class).getSingleResult();

        return cliente;

    }
}