package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public class ConsultaEstaApagado extends ConsultaEstado {
    public ConsultaEstaApagado(DispositivoInteligente dispInteligente) {
        super(dispInteligente);
    }

    @Override
    public boolean consultar() {
        return this.inteligente.estaApagado();
    }
}
