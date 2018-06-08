package Clases.Dispositivo;

import Clases.Fabricante;
import Clases.Usuario.Cliente;

import java.util.List;

public class Convertidor {


    public void convertirInteligente (DispositivoEstandar dispositivoBasico, List<DispositivoEstandar> estandares, List<DispositivoInteligente> inteligentes){

        DispositivoEstandarInteligente nuevo = new DispositivoEstandarInteligente(dispositivoBasico);
        estandares.remove(dispositivoBasico);
        inteligentes.add(nuevo);
    }



}
