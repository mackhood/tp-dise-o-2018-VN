package dominio.actuador;

import java.util.List;

import dominio.dispositivo.DispositivoInteligente;

public abstract class Actuador {
	// Cada uno de los distintos Actuadores va a tener su propia logica a
	// implementar en ejecutar
	// El actuador sabe a que dispositivo tiene que afectar
	// DispositivoInteligente dispInteligente = null;

	protected List<DispositivoInteligente> dispositivos;

	public Actuador(List<DispositivoInteligente> dispositivosAfectados) {
		dispositivos = dispositivosAfectados;
	};

	public abstract void ejecutar();

	public void asignarDispositivo(DispositivoInteligente disp) {

		dispositivos.add(disp);
	}

	public void asignarGrupo(List<DispositivoInteligente> nuevosDispositivos) {

		dispositivos.addAll(nuevosDispositivos);
	}
}
