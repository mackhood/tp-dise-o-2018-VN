package dominio.sensor;

import dominio.regla.Regla;

public class CondicionPorIgual extends Condicion {

	public CondicionPorIgual(Regla regla, double valorLimite, String tipo) {
		super(regla, valorLimite,tipo);
	}

	@Override
	public boolean cumpleCondicion() {
		
		return medicionActual == valorLimite;
	}
}

