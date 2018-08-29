package dominio.sensor;

import dominio.regla.Regla;

public abstract class Condicion {

	protected double valorLimite;
	private String tipo;
	protected double medicionActual;
	private Regla regla;
	private Estado estado = new Pendiente();

	public Condicion(Regla regla, double valorLimite, String tipo) {

		this.regla = regla;
		this.valorLimite = valorLimite;
		this.tipo = tipo;
	}

	/*
	 * Esto sigue la siguiente logica: al instanciar una condicion, se define lo que
	 * llame valor limite y si el valor medido esta por abajo o por arriba de este
	 * dependiendo si la cond es por mayor o por menor, se considera cumplida la
	 * misma. Lo pense de esta manera porque Franco nos dijo que podiamos tomar
	 * todas las mediciones como double y por esto mismo para sensar movimiento
	 * utilizo "booleanos" de la forma <0/1>. Todas las mediciones quedan como
	 * double en cuanto a su valor y se diferencian por su tipo
	 */

	public abstract boolean cumpleCondicion();

	public void actualizar(Sensor unSensor) {

		medicionActual = unSensor.getValorMedicion();

		if (this.cumpleCondicion()) {

			this.estado = new Cumplida();
			regla.serNotificadaPor(this);
		}
	}

	public String getTipo() {

		return tipo;
	}

	public double getMedicionActual() {

		return medicionActual;
	}

	public Estado getEstado() {

		return estado;
	}
}