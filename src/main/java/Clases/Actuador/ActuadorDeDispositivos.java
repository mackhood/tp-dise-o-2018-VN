package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;

public abstract class  ActuadorDeDispositivos {

    List<DispositivoInteligente> dispositivoInteligenteList = new ArrayList<>();

    ActuadorDeDispositivos(List<DispositivoInteligente> disp) {
        this.dispositivoInteligenteList.addAll(disp);
    }

}
