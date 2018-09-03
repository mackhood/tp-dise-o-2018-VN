
package dominio.dispositivo;

public abstract class Dispositivo {

	protected String nombre;
	protected double consumoEstimadoPorHora;
	protected String equipoConcreto;
	protected boolean esBajoConsumo;
	protected double horasDeUso = 0;
	protected double restriccionMinima;
	protected double restriccionMaxima;
	protected double horasMaximaPorConsumo;

	public double getHorasMaximaPorConsumo() {
		return horasMaximaPorConsumo;
	}

	public void setHorasMaximaPorConsumo(double horasMaximaPorConsumo) {
		this.horasMaximaPorConsumo = horasMaximaPorConsumo;
	}

	public double consumoEstimadoPorHora() {
		return consumoEstimadoPorHora;
	}

	public String getNombre() {

		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getConsumoEstimadoPorHora() {
		return consumoEstimadoPorHora;
	}

	public void setConsumoEstimadoPorHora(double consumoEstimadoPorHora) {
		this.consumoEstimadoPorHora = consumoEstimadoPorHora;
	}

	public double getHorasDeUso() {
		return horasDeUso;
	}

	public void setHorasDeUso(double horasDeUso) {
		this.horasDeUso = horasDeUso;
	}

	public double getConsumoTotal() {

		return consumoEstimadoPorHora * horasDeUso;
	}

	public void aumentarConsumoPor(int aumento) {
		consumoEstimadoPorHora = consumoEstimadoPorHora + aumento;
	}

	public abstract int getPuntos();

	public String getEquipoConcreto() {
		return equipoConcreto;
	}

	public double getRestriccionMinima() {
		return restriccionMinima;
	}

	public void setRestriccionMinima(double restriccionMinima) {
		this.restriccionMinima = restriccionMinima;
	}

	public double getRestriccionMaxima() {
		return restriccionMaxima;
	}

	public void setRestriccionMaxima(double restriccionMaxima) {
		this.restriccionMaxima = restriccionMaxima;
	}
}
