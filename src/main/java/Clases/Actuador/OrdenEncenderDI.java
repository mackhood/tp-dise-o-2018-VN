package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;

public class OrdenEncenderDI extends  ActuadorDeDispositivos implements Actuador {


    public OrdenEncenderDI(List<DispositivoInteligente> dispInteligente) {
                  super(dispInteligente);
    }

    @Override
    public void ejecutar() {

        dispositivoInteligenteList.stream().forEach(DispositivoInteligente::encender);

    }


}
