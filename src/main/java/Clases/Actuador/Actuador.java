package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public interface Actuador {
    //Cada uno de los distintos Actuadores va a tener su propia logica a implementar en ejecutar
    //El actuador sabe a que dispositivo tiene que afectar
    DispositivoInteligente dispInteligente = null;

    public abstract void ejecutar();


}
