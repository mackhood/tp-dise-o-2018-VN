package dominio.sensor;

import dominio.regla.Regla;

public class CondicionPorMayor extends Condicion {

	public CondicionPorMayor(Regla regla, double valorLimite, String tipo) {
		super(regla, valorLimite, tipo);
	}

	@Override
	public boolean cumpleCondicion() {

		return medicionActual > valorLimite;
	}

}
