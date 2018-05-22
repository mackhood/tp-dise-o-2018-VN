package Clases.Dispositivo;

import Clases.Fabricante;

public class Convertidor {


    public DispositivoEstandarInteligente convertirInteligente (DispositivoEstandar dispositivoBasico , DispositivoEstandarInteligente nuevo ){


        nuevo.setNombre(dispositivoBasico.getNombre());
        nuevo.setConsumoPorHora(dispositivoBasico.consumoEstimadoPorHora());
        nuevo.setHorasDeUso( dispositivoBasico.getHorasDeUso());
        nuevo.SetFabricante(dispositivoBasico.getFabricante()) ;
        nuevo.SetIdInteligente(dispositivoBasico.getIdEstandar());
        nuevo.cambiarEstado(new EstadoApagado());


        return nuevo;
    }



}
