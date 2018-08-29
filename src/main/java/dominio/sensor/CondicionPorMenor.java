package dominio.sensor;

import dominio.regla.Regla;

public class CondicionPorMenor extends Condicion {

	public CondicionPorMenor(Regla regla, double valorLimite, String tipo) {
		super(regla, valorLimite, tipo);
	};

	@Override
	public boolean cumpleCondicion() {

		return medicionActual < valorLimite;
	}

}
