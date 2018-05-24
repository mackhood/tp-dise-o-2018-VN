package Clases.Actuador;

import Clases.Dispositivo.DispositivoInteligente;

public class OrdenSubirIntensidad extends Actuador {

    public OrdenSubirIntensidad(DispositivoInteligente unDI){ super(unDI); }

    public void ejecutar() {
    
		dispInteligente.aumentarConsumoPor(50);
    }
}