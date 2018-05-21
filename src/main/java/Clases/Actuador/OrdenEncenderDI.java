package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public class OrdenEncenderDI extends Actuador{

    public OrdenEncenderDI(DispositivoInteligente dispInteligente) {
        super(dispInteligente);
    }

    @Override
    public void ejecutar() {
        this.dispInteligente.encender();
    }


}
