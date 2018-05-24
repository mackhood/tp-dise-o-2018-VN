package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public abstract class ConsultaConsumo {

    DispositivoInteligente unDI;
    double ultimasNHoras;

    public ConsultaConsumo(){}

    public ConsultaConsumo(DispositivoInteligente dispInteligente, double ultimasNHoras) {
        this.unDI = dispInteligente;
        this.ultimasNHoras = ultimasNHoras;
    }

    public abstract double consultar();

    public abstract double consultarDI(DispositivoInteligente unDI);
}