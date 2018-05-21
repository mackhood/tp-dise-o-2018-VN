package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public class OrdenApagarDI extends Actuador{

    public OrdenApagarDI(DispositivoInteligente dispInteligente) {
        super(dispInteligente);
    }

    @Override
    public void ejecutar() {
        this.dispInteligente.apagar();
    }
}
