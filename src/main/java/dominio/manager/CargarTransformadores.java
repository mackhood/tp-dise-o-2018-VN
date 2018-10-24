package dominio.manager;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.transformador.Transformador;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import dominio.zonageografica.Ubicacion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CargarTransformadores implements WithGlobalEntityManager, TransactionalOps {
    public void persistirTransformadores() {
        withTransaction(() -> {
            Domicilio domicilio = new Domicilio("av cordoba", 1234, 7, 'A');
            ID id = new ID(TiposId.DNI, "10125789");
            DispositivoEstandar dispEstandar = new DispositivoEstandar.DispositivoEstandarBuilder("reloj")
                    .consumoEstimadoPorHora(5.0).equipoConcreto("reloj").horasDeUso(4.0).build();
            List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();
            dispositivosEstandares.add(dispEstandar);

            DispositivoInteligente aireAcondicionado3500 = new DispositivoInteligente.DispositivoInteligenteBuilder(
                    "aireAcondicionado").equipoConcreto("De 3500 frigorias").horasDeUso(5.0)
                    .consumoEstimadoPorHora((double) 1.613).build();

            List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();
            dispositivosInteligentes.add(aireAcondicionado3500);

            Cliente unCliente = new Cliente("ariel", "galvan", "galvanariel97","password", id, domicilio, 47581269,
                    dispositivosEstandares, dispositivosInteligentes);

            Ubicacion ubicacion = new Ubicacion(5, 2);
            unCliente.setUbicacion(ubicacion);
            List<Cliente> listaClientesConectados = new ArrayList<>();
            listaClientesConectados.add(unCliente);
            Ubicacion ubicacionTransformador = new Ubicacion(3, 3);
            Transformador transformador = new Transformador(new ArrayList<>(), ubicacionTransformador);
            entityManager().persist(transformador);
            entityManager().getTransaction().commit();
        });
    }
}
