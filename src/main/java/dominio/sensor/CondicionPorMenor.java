package dominio.sensor;

import dominio.regla.Regla;

public class CondicionPorMenor extends Condicion {
	
	public CondicionPorMenor(Regla regla, double valorLimite) {
		super(regla,valorLimite);};

	@Override
	public boolean cumpleCondicion() {
		
		return medicionActual < valorLimite;
	}

}
