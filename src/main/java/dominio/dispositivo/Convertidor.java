package dominio.dispositivo;

import dominio.usuario.Cliente;

import java.util.List;

public class Convertidor {



    public void convertirInteligente (Cliente cliente, DispositivoEstandar dispositivoBasico){

        DispositivoEstandarInteligente nuevo = new DispositivoEstandarInteligente(dispositivoBasico);
       cliente.sacarDispositivoEstandarLista(dispositivoBasico);
        cliente.agregarDispositivoInteligente(nuevo);
    }



    
}
