package Dominio.Actuador;

import Dominio.Dispositivo.DispositivoInteligente;

import java.util.List;

public class OrdenEncenderDI extends  ActuadorDeDispositivos implements Actuador {


    public OrdenEncenderDI(List<DispositivoInteligente> dispInteligente) {
                  super(dispInteligente);
    }

    @Override
    public void ejecutar() {

        dispositivosInteligentes.stream().forEach(DispositivoInteligente::encender);

    }


}
