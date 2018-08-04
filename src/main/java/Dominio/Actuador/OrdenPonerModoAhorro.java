package Dominio.Actuador;

import Dominio.Dispositivo.DispositivoInteligente;

import java.util.List;

public class OrdenPonerModoAhorro extends  ActuadorDeDispositivos implements  Actuador{

    public OrdenPonerModoAhorro(List<DispositivoInteligente> dispInteligente) {

        super(dispInteligente);
    }

    @Override
    public void ejecutar() {
        dispositivoInteligenteList.stream().forEach(DispositivoInteligente::ponerModoAhorro);
    }
}
