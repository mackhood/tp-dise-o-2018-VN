package dominio.usuario;

import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCliente {
    public static RepositorioCliente instance = new RepositorioCliente();
    private List<Cliente> clientes = new ArrayList<>();
    List<DispositivoEstandar> dispositivoEstandars = new ArrayList<>();
    List<DispositivoInteligente> dispositivoInteligentes = new ArrayList<>();

    private RepositorioCliente(){

        Domicilio domicilio = new Domicilio("av cordoba",1234,7,'A');
        ID id = new ID(TiposId.DNI,"10125789");
        Cliente unCliente = new Cliente("ariel","galvan","galvanariel97",id,domicilio,47581269,dispositivoEstandars,dispositivoInteligentes);


    }
}
