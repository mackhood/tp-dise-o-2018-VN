package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public abstract class ConsultaConsumo {
    DispositivoInteligente inteligente;

    public ConsultaConsumo(DispositivoInteligente dispInteligente) {
        this.inteligente = dispInteligente;
    }

    public abstract double consultar();
}
