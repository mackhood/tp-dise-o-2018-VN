package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public class OrdenPonerModoAhorro extends Actuador{

    public OrdenPonerModoAhorro(DispositivoInteligente dispInteligente) {
        super(dispInteligente);
    }

    @Override
    public void ejecutar() {
        dispInteligente.ponerModoAhorro();
    }
}
