package Dominio.Actuador;

import Dominio.Dispositivo.DispositivoInteligente;

import java.util.List;

public class OrdenApagarDI extends  ActuadorDeDispositivos implements Actuador{

    public OrdenApagarDI(List<DispositivoInteligente> dispInteligente) {
        super(dispInteligente);
    }

    @Override
    public void ejecutar() {
        dispositivosInteligentes.stream().forEach(DispositivoInteligente::apagar);
    }
}
