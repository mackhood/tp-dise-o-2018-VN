package dominio.actuador;

import dominio.dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;

public class OrdenPonerModoAhorro extends Actuador {

	public OrdenPonerModoAhorro(List<DispositivoInteligente> dispositivosAfectados) {
		super(dispositivosAfectados);
	}

	@Override
	public void ejecutar() {

		dispositivos.forEach(DispositivoInteligente::ponerModoAhorro);

	}
}