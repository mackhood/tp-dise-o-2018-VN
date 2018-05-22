package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public class ConsultaEstaEncendido extends ConsultaEstado {

    public ConsultaEstaEncendido(){}
    public ConsultaEstaEncendido(DispositivoInteligente dispInteligente) {
        super(dispInteligente);
    }

    @Override
    public boolean consultar() {
        return this.inteligente.estaEncendido();
    }

    @Override
    public boolean consultarDI(DispositivoInteligente unDI)
    {
        return unDI.estaEncendido();
    }


}
