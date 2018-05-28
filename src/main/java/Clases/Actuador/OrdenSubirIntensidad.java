package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

import java.util.List;

public class OrdenSubirIntensidad extends ActuadorDeDispositivos implements  Actuador {

    public OrdenSubirIntensidad(List<DispositivoInteligente> unDI){ super(unDI); }

    public void ejecutar() {
        dispositivoInteligenteList.stream().forEach(x-> x.aumentarConsumoPor(50));
    }
}