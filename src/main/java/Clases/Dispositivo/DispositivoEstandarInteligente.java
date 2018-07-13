package Clases.Dispositivo;

import Clases.Fabricante;

public class DispositivoEstandarInteligente extends DispositivoInteligente {


    DispositivoEstandar  dispositivoEstandar ;

    public DispositivoEstandarInteligente(DispositivoEstandar dispositivoEstandar) {
        super (dispositivoEstandar.getNombre(),dispositivoEstandar.getConsumoEstimadoPorHora());
        this.dispositivoEstandar = dispositivoEstandar ;
        this.horasDeUso = dispositivoEstandar.getHorasDeUso();
    }

    @Override
    public int getPuntos() {
        return 10;
    }
}
