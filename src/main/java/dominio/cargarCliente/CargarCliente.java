package dominio.cargarCliente;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import dominio.usuario.Cliente;
import dominio.usuario.Domicilio;
import dominio.usuario.ID;
import dominio.usuario.TiposId;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import java.util.ArrayList;
import java.util.List;

public class CargarCliente implements WithGlobalEntityManager,TransactionalOps {

    public void run()
    {
        withTransaction(()->{
            Domicilio domicilio = new Domicilio("av cordoba",1234,7,'A');
            ID id = new ID(TiposId.DNI,"10125789");
            DispositivoEstandar dispEstandar = new DispositivoEstandar.DispositivoEstandarBuilder("reloj").consumoEstimadoPorHora(5.0).equipoConcreto("reloj").horasDeUso(4.0).build();
            List<DispositivoEstandar> dispositivosEstandares = new ArrayList<>();
            dispositivosEstandares.add(dispEstandar);

            DispositivoInteligente aireAcondicionado3500 = new DispositivoInteligente.DispositivoInteligenteBuilder("aireAcondicionado").equipoConcreto("De 3500 frigorias").horasDeUso(5.0).consumoEstimadoPorHora((double) 1.613).build();

            List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();
            dispositivosInteligentes.add(aireAcondicionado3500);
            Cliente unCliente = new Cliente("ariel","galvan","galvanariel97",id,domicilio,47581269,dispositivosEstandares,dispositivosInteligentes);

            entityManager().persist(unCliente);
            entityManager().getTransaction().commit();
        });
    }
}
