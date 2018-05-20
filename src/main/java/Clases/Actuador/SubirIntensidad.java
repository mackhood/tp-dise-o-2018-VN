package Clases.Actuador;

import Clases.DispositivoInteligente;

public class SubirIntensidad extends Actuador {
    public SubirIntensidad(DispositivoInteligente dispInteligente) {
        super(dispInteligente);
    }

    @Override
    public void ejecutar() {
        dispInteligente.aumentarConsumoPor(150);
    }
}
