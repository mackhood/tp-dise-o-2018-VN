package Clases.Dispositivo;

import Clases.Fabricante;

public class Convertidor {


    public DispositivoEstandarInteligente convertirInteligente (DispositivoEstandar dispositivoBasico  ){

        DispositivoEstandarInteligente nuevo = new DispositivoEstandarInteligente(dispositivoBasico,dispositivoBasico.getNombre(),122,null);



        return nuevo;
    }



}
