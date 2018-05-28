package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

import java.util.List;

public class OrdenApagarDI extends  ActuadorDeDispositivos implements Actuador{

    public OrdenApagarDI(List<DispositivoInteligente> dispInteligente) {
        super(dispInteligente);
    }

    @Override
    public void ejecutar() {
        dispositivoInteligenteList.stream().forEach(DispositivoInteligente::apagar);
    }
}
