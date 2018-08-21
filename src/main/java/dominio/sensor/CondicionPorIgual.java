package dominio.sensor;

import dominio.regla.Regla;

public class CondicionPorIgual extends Condicion {

	public CondicionPorIgual(Regla regla, double valorLimite) {
		super(regla, valorLimite);
	}

	@Override
	public boolean cumpleCondicion() {
		
		return medicionActual == valorLimite;
	}

}

