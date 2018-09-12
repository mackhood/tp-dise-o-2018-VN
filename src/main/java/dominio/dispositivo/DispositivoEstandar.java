package dominio.dispositivo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DispositivoEstandar extends Dispositivo {

	@GeneratedValue
	@Id
	private Long id;


	private DispositivoEstandar(DispositivoEstandarBuilder builder) {
		this.nombre = builder.nombre;
		this.consumoEstimadoPorHora = builder.consumoEstimadoPorHora;
		this.equipoConcreto = builder.equipoConcreto;
		this.esBajoConsumo = builder.esBajoConsumo;
		this.horasDeUso = builder.horasDeUso;
		this.restriccionMinima = builder.restriccionMinima;
		this.restriccionMaxima = builder.restriccionMaxima;
		this.horasMaximaRecomendadaPorConsumo = builder.horasMaximaRecomendadaPorConsumo;
	}

	public double getHorasDeUso() {
		return horasDeUso;
	}

	public double getConsumoEstimadoPorHora() {
		return consumoEstimadoPorHora;
	}

	public String getNombre() {
		return nombre;
	}

	public double getConsumoTotal() {
		return this.consumoEstimadoPorHora * this.horasDeUso;
	}

	public int getPuntos() {
		return 0;
	}

	public void serUsado(int cantHorasEstimativa) {
		horasDeUso = horasDeUso + cantHorasEstimativa;
	}

	public static class DispositivoEstandarBuilder {

		private final String nombre;
		private double consumoEstimadoPorHora;
		private String equipoConcreto;
		private boolean esBajoConsumo;
		private double horasDeUso = 0;
		private double restriccionMinima;
		private double restriccionMaxima;
		private double horasMaximaRecomendadaPorConsumo;

		public DispositivoEstandarBuilder(String firstName) {
			this.nombre = firstName;

		}

		public DispositivoEstandarBuilder consumoEstimadoPorHora(Double consumoEstimadoPorHora) {
			this.consumoEstimadoPorHora = consumoEstimadoPorHora;
			return this;
		}

		public DispositivoEstandarBuilder equipoConcreto(String equipoConcreto) {
			this.equipoConcreto = equipoConcreto;
			return this;
		}

		public DispositivoEstandarBuilder esBajoConsumo(Boolean esBajoConsumo) {
			this.esBajoConsumo = esBajoConsumo;
			return this;
		}

		public DispositivoEstandarBuilder restriccionMinima(Double restriccionMinima) {
			this.restriccionMinima = restriccionMinima;
			return this;
		}

		public DispositivoEstandarBuilder restriccionMaxima(Double restriccionMaxima) {
			this.restriccionMaxima = restriccionMaxima;
			return this;
		}

		public DispositivoEstandarBuilder horasDeUso(Double horasDeUso) {
			this.restriccionMaxima = horasDeUso;
			return this;
		}

		public DispositivoEstandarBuilder horasMaximaRecomendadaPorConsumo(Double horasMaximaPorConsumo) {
			this.horasMaximaRecomendadaPorConsumo = horasMaximaPorConsumo;
			return this;
		}

		public DispositivoEstandar build() {
			return new DispositivoEstandar(this);
		}

	}

}
