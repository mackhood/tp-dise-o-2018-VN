package Dominio.Actuador;

import Dominio.Dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;

public abstract class  ActuadorDeDispositivos {

    List<DispositivoInteligente> dispositivosInteligentes = new ArrayList<>();

    ActuadorDeDispositivos(List<DispositivoInteligente> disp) {
        this.dispositivosInteligentes.addAll(disp);
    }

}
