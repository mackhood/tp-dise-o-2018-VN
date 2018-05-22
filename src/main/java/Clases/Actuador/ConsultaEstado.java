package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public abstract class ConsultaEstado {
    DispositivoInteligente inteligente;
    public ConsultaEstado(){}

    public ConsultaEstado(DispositivoInteligente dispInteligente) {
        this.inteligente = dispInteligente;
    }

    public abstract boolean consultar();

    public void setDI(DispositivoInteligente unDI){inteligente = unDI;}

    public abstract boolean consultarDI(DispositivoInteligente unDI);

}
