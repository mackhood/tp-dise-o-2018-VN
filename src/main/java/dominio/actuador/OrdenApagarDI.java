package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;

public class OrdenApagarDI extends Actuador {

	public OrdenApagarDI(List<DispositivoInteligente> dispositivosAfectados) {
		super(dispositivosAfectados);
	}

	@Override
	public void ejecutar() {
		dispositivos.forEach(DispositivoInteligente::apagar);
	}

	@Override
	public void ejecutarInversa() {
		dispositivos.forEach(DispositivoInteligente::encender);
	}
}
