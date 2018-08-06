package Dominio.Actuador;

import Dominio.Dispositivo.DispositivoInteligente;

import java.util.List;

public class OrdenSubirIntensidad extends ActuadorDeDispositivos implements  Actuador {

    public OrdenSubirIntensidad(List<DispositivoInteligente> unDI){ super(unDI); }

    public void ejecutar() {
        dispositivosInteligentes.stream().forEach(x-> x.aumentarConsumoPor(50));
    }
}