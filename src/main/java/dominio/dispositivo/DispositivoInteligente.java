package dominio.dispositivo;

import com.sun.prism.shader.DrawSemiRoundRect_ImagePattern_Loader;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DispositivoInteligente extends Dispositivo {

	public  EstadoDispositivo estadoDispositivo;
	public   LocalDateTime horaEncendido ;
	public  LocalDateTime horaApagado ;



	public DispositivoInteligente(DispositivoInteligenteBuilder builder) {
		this.nombre = builder.nombre;
		this.consumoEstimadoPorHora = builder.consumoEstimadoPorHora;
		this.equipoConcreto = builder.equipoConcreto;
		this.esBajoConsumo = builder.esBajoConsumo;
		this.horasDeUso = builder.horasDeUso;
		this.restriccionMinima = builder.restriccionMinima;
		this.restriccionMaxima = builder.restriccionMaxima;
		this.horasMaximaPorConsumo = builder.horasMaximaPorConsumo;
		this.estadoDispositivo=builder.estadoDispositivo;
		this.horaEncendido=builder.horaEncendido;
		this.horaApagado=builder.horaApagado;





	}

	public DispositivoInteligente() {

	}


	/*

	private EstadoDispositivo estadoDispositivo;
	public LocalDateTime horaEncendido = null;
	public LocalDateTime horaApagado = null;



	public DispositivoInteligente(String nombre, double consumoEstimadoPorHora) {

		this.nombre = nombre;
		this.consumoEstimadoPorHora = consumoEstimadoPorHora;
		estadoDispositivo = new EstadoApagado();
	}

	public DispositivoInteligente(String nombre, double restriccionMinima, double restriccionMaxima) {
		this.nombre = nombre;
		this.restriccionMinima = restriccionMinima;
		this.restriccionMaxima = restriccionMaxima;
	}

	public DispositivoInteligente(String nombre, String equipoConcreto, boolean esBajoConsumo,
			double consumoEstimadoPorHora, double restriccionMinima, double restriccionMaxima) {
		this.nombre = nombre;
		estadoDispositivo = new EstadoApagado();
		this.equipoConcreto = equipoConcreto;
		this.esBajoConsumo = esBajoConsumo;
		this.consumoEstimadoPorHora = consumoEstimadoPorHora;
		this.restriccionMinima = restriccionMinima;
		this.restriccionMaxima = restriccionMaxima;
	}

	public DispositivoInteligente(String nombre, String equipoConcreto, boolean esBajoConsumo,
			double consumoEstimadoPorHora) {
		this.nombre = nombre;
		estadoDispositivo = new EstadoApagado();
		this.equipoConcreto = equipoConcreto;
		this.esBajoConsumo = esBajoConsumo;
		this.consumoEstimadoPorHora = consumoEstimadoPorHora;
	}

	public DispositivoInteligente(String nombre, String equipoConcreto, boolean esBajoConsumo) {
		this.nombre = nombre;
		estadoDispositivo = new EstadoApagado();
		this.equipoConcreto = equipoConcreto;
		this.esBajoConsumo = esBajoConsumo;
	}
	*/
	public void setHoraEncendido(LocalDateTime horaEncendido) {
		this.horaEncendido = horaEncendido;
	}

	public void setHoraApagado(LocalDateTime horaApagado) {
		this.horaApagado = horaApagado;
	}

	public LocalDateTime getHoraEncendido() {
		return horaEncendido;
	}

	public LocalDateTime getHoraApagado() {
		return horaApagado;
	}

	public EstadoDispositivo estadoDispositivo() {
		return estadoDispositivo;
	}

	public double getConsumoEstimadoPorHora() {

		return consumoEstimadoPorHora;
	}

	public boolean esCiertoEstado(EstadoDispositivo estadoCond) {
		return estadoDispositivo.equals(estadoCond);
	}

	public void setHorasDeUso(double horas) {

		this.horasDeUso = horas;
	}

	public boolean estaEncendido() {
		return estadoDispositivo.estaEncendido();
	}

	public boolean estaApagado() {
		return estadoDispositivo.estaApagado();
	}

	public void apagar() {
		estadoDispositivo.apagar(this);
	}

	public void encender() {

		estadoDispositivo.encender(this);
	}

	public void ponerModoAhorro() {
		estadoDispositivo.ponerModoAhorro(this);
	}

	public void sumarHorasDeUso(LocalDateTime unHorario, LocalDateTime otroHorario) {

		horasDeUso = horasDeUso + unHorario.until(otroHorario, ChronoUnit.HOURS);
	}

	/*
	 * Para este metodo de abajo no se me ocurre otra idea que hacer varios if con
	 * las distintas situaciones tipo si la hora de encendido y apagado estan antes
	 * del intervalo de consulta, etc. Si alguien tiene una mejor idea u otra forma
	 * de implementarlo buenisimo.
	 */

	public double consumoUltimasNHoras(double horas) {
		if (horas > horasDeUso) {
			return consumoEstimadoPorHora * horasDeUso;
		} else {
			return consumoEstimadoPorHora * horas;
		}
	}

	public void cambiarEstado(EstadoDispositivo estadoNuevo) {

		estadoDispositivo = estadoNuevo;
	}

	public void ejecutar(DispositivoFisico dispositivoFisico) {

		dispositivoFisico.ejecutar();
	}

	public void aumentarConsumoPor(double cantidad) {

		this.consumoEstimadoPorHora += cantidad;
	}

	public void reducirConsumoPor(double cantidad) {

		this.consumoEstimadoPorHora -= cantidad;
	}

	public double getConsumoTotal() {

		return horasDeUso * consumoEstimadoPorHora;
	}

	@Override
	public boolean esInteligente() {
		return true;
	}

	public boolean estaEnModoAhorro() {

		return estadoDispositivo.estaEnModoAhorro();
	}

	public int getPuntos() {
		return 15;
	}






	public static class DispositivoInteligenteBuilder {



		private EstadoDispositivo estadoDispositivo= new EstadoApagado();
		private LocalDateTime horaEncendido = null;
		private LocalDateTime horaApagado = null;
		private  final String nombre;
		private double consumoEstimadoPorHora;
		private String equipoConcreto;
		private boolean esBajoConsumo;
		private double horasDeUso = 0;
		private double restriccionMinima;
		private double restriccionMaxima;
		private double horasMaximaPorConsumo;



		public DispositivoInteligenteBuilder  (String nombre) {
			this.nombre = nombre;

		}

		public DispositivoInteligenteBuilder horaEncendido(LocalDateTime horaEncendido ) {

			this.horaEncendido=horaEncendido;
			return  this;

		}

		public DispositivoInteligenteBuilder horaApagado (LocalDateTime horaApagado){

			this.horaApagado=horaApagado;
			return this;
		}

		public DispositivoInteligenteBuilder estadoDispositivo (EstadoDispositivo estadoDispositivo){

			this.estadoDispositivo=estadoDispositivo;
			return this;

		}


		public DispositivoInteligenteBuilder consumoEstimadoPorHora(Double consumoEstimadoPorHora) {
			this.consumoEstimadoPorHora = consumoEstimadoPorHora;
			return this;
		}

		public DispositivoInteligenteBuilder equipoConcreto(String equipoConcreto) {
			this.equipoConcreto = equipoConcreto;
			return this;
		}

		public DispositivoInteligenteBuilder esBajoConsumo(Boolean esBajoConsumo) {
			this.esBajoConsumo = esBajoConsumo;
			return this;
		}

		public DispositivoInteligenteBuilder restriccionMinima(Double restriccionMinima) {
			this.restriccionMinima = restriccionMinima;
			return this;
		}

		public DispositivoInteligenteBuilder restriccionMaxima(Double restriccionMaxima) {
			this.restriccionMaxima = restriccionMaxima;
			return this;
		}

		public DispositivoInteligenteBuilder horasDeUso(Double horasDeUso) {
			this.restriccionMaxima = horasDeUso;
			return this;
		}

		public DispositivoInteligenteBuilder horasMaximaPorConsumo(Double horasMaximaPorConsumo) {
			this.restriccionMaxima = horasMaximaPorConsumo;
			return this;
		}

		public DispositivoInteligente build() {
			return new DispositivoInteligente(this);
		}

	}














}



