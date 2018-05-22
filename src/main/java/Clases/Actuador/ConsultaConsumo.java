package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public abstract class ConsultaConsumo {
    DispositivoInteligente unDI;
    double ultimasXHoras;

    public ConsultaConsumo(){}
    public ConsultaConsumo(DispositivoInteligente dispInteligente, double ultimasXHoras) {
        this.unDI = dispInteligente;
        this.ultimasXHoras = ultimasXHoras;
    }

    public abstract double consultar();

    public abstract double consultarDI(DispositivoInteligente unDI);
}
