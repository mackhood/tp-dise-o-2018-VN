package Clases.Dispositivo;

import Clases.Fabricante;

public class DispositivoEstandarInteligente extends DispositivoInteligente {


    DispositivoEstandar  dispositivoEstandar ;

    public DispositivoEstandarInteligente(DispositivoEstandar dispositivoEstandar,String nombre, int idInteligente, Fabricante unFabricante) {
        super (nombre, idInteligente, unFabricante);
        this.dispositivoEstandar = dispositivoEstandar ;
        this.setConsumoPorHora(dispositivoEstandar.consumoEstimadoPorHora());
    }

    @Override
    public int getPuntos() {
        return 15;
    }
}
