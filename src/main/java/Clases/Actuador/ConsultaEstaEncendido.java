package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public class ConsultaEstaEncendido extends ConsultaEstado {

    public ConsultaEstaEncendido(DispositivoInteligente dispInteligente) {
        super(dispInteligente);
    }

    @Override
    public boolean consultar() {
        return this.inteligente.estaEncendido();
    }
}
