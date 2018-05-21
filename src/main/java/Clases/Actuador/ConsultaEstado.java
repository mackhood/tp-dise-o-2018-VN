package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public abstract class ConsultaEstado {
    DispositivoInteligente inteligente;

    public ConsultaEstado(DispositivoInteligente dispInteligente) {
        this.inteligente = dispInteligente;
    }

    public abstract boolean consultar();
}
