package dominio.sensor;

import dominio.regla.Regla;

public class CondicionPorMayor extends Condicion {

	public CondicionPorMayor(Regla regla, double valorLimite) {
		super(regla, valorLimite);
	}

	@Override
	public boolean cumpleCondicion() {
		
		return medicionActual > valorLimite;
	}

}
