package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;

public class OrdenSubirIntensidad extends Actuador {

	public OrdenSubirIntensidad(List<DispositivoInteligente> dispositivosAfectados) {
		super(dispositivosAfectados);
	}

	@Override
	public void ejecutar() {

		dispositivos.forEach(d -> d.aumentarConsumoPor(50));
	}
	
	@Override
	public void ejecutarInversa() {
		
		dispositivos.forEach(d -> d.reducirConsumoPor(50));
	}
}