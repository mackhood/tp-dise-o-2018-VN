package dominio.categoria;

import dominio.usuario.*;

import java.util.Date;
import java.util.Timer;

public class ActualizadorAutomatizado {

	AccionProgramada accion = new AccionProgramada("Actualizar categoria- ");

	public AsignadorDeCategoria asignador() {
		return AsignadorDeCategoria.getInstance();
	}

	public void ejecutarTareaPara(Cliente unCliente, Date horarioDeEjecucion) {

		Timer tareaProgramada = new Timer();

		tareaProgramada.scheduleAtFixedRate(accion, horarioDeEjecucion, 3600000);

	}

}
