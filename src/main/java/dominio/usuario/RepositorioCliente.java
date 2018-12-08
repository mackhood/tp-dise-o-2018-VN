package dominio.usuario;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCliente implements WithGlobalEntityManager {
    public static RepositorioCliente instance = new RepositorioCliente();
    List<DispositivoEstandar> dispositivoEstandars = new ArrayList<>();
    List<DispositivoInteligente> dispositivoInteligentes = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    private RepositorioCliente() {

        Domicilio domicilio = new Domicilio("av cordoba", 1234, 7, 'A');
        ID id = new ID(TiposId.DNI, "10125789");
        Cliente unCliente = new Cliente("ariel", "galvan", "galvanariel97", "password", id, domicilio, 47581269, dispositivoEstandars, dispositivoInteligentes);


    }

}
