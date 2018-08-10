package Dominio.Dispositivo;

import Dominio.Usuario.Cliente;

import java.util.List;

public class Convertidor {



    public void convertirInteligente (Cliente cliente, DispositivoEstandar dispositivoBasico, List<DispositivoInteligente> inteligentes){

        DispositivoEstandarInteligente nuevo = new DispositivoEstandarInteligente(dispositivoBasico);
       cliente.sacarDispositivoEstandarLista(dispositivoBasico);
        cliente.agregarDispositivoInteligente(nuevo);
    }



}
