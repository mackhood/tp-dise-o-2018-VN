package dominio.dispositivo;

import net.bytebuddy.dynamic.TypeResolutionStrategy;
import org.eclipse.jetty.util.annotation.Name;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "dispositivoInteligente")

public class DispositivoInteligente extends Dispositivo {

	@Enumerated(EnumType.STRING)
	public EstadoDispositivo estadoDispositivo;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idDispositivo")
	public List<Intervalo> intervalosDeUso = new ArrayList<>();

	//@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//private Intervalo intervaloEncendido = new Intervalo();

	public DispositivoInteligente(DispositivoInteligenteBuilder builder) {
		this.nombre = builder.nombre;
		this.consumoEstimadoPorHora = builder.consumoEstimadoPorHora;
		this.equipoConcreto = builder.equipoConcreto;
		this.estadoDispositivo = builder.estadoDispositivo;
		this.tipoDispositivo = builder.tipoDispositivo;
		this.intervalosDeUso = builder.intervalosDeUso;

	}


	public DispositivoInteligente() {
	}

	public EstadoDispositivo estadoDispositivo() {
		return estadoDispositivo;
	}

	public double getConsumoEstimadoPorHora() {

		return consumoEstimadoPorHora;
	}

	public EstadoDispositivo getEstadoDispositivo() {
		return estadoDispositivo;
	}

	public boolean estaEncendido() {
		return estadoDispositivo.estaEncendido();
	}

	public boolean estaApagado() {
		return estadoDispositivo.estaApagado();
	}

	public void apagar() {

		if (this.estaEncendido() || this.estaEnModoAhorro()) {

			estadoDispositivo.apagar(this);
			Intervalo i = intervalosDeUso.get(intervalosDeUso.size() - 1);
			i.setFin(LocalDateTime.now());
			intervalosDeUso.remove(intervalosDeUso.size() -1);
			intervalosDeUso.add(i);
			System.out.println("APAGAR");
		}

		else {

			estadoDispositivo.apagar(this);
		}
	}

	public List<Intervalo> encendidoEntre(LocalDateTime fecha, LocalDateTime otraFecha) {

		return intervalosDeUso.stream().filter(i -> i.estaEntre(fecha, otraFecha)).collect(Collectors.toList());
	}

	public void agregarListaIntervalos(List<Intervalo> intervalos) {
		this.intervalosDeUso = intervalos;
	}

	public double consumoParaIntervalo(Intervalo i) {

		// System.out.println("intervalo en horas " + i.intervaloEnHoras());
		// System.out.println("consumo por hora" + consumoEstimadoPorHora);
		return i.intervaloEnHoras() * consumoEstimadoPorHora;
	}

	public double consumoParaPeriodo(Periodo p) {

		Intervalo periodo = p.convertir();
		double horasDeUso = intervalosDeUso.stream().mapToDouble(i -> i.horasDentroDe(periodo)).sum();
		return horasDeUso*consumoEstimadoPorHora;
	}

	public double consumoParaIntervalos(List<Intervalo> intervalos) {

		// System.out.println("intervalos tamanio:" + intervalos.size());
		if(estadoDispositivo.estaEncendido())
		{
			return intervalos.subList(0,intervalos.size()-1).stream().mapToDouble(i -> consumoParaIntervalo(i)).sum();
		}
		else
		{
			return intervalos.stream().mapToDouble(i -> consumoParaIntervalo(i)).sum();

		}
	}

	public void encender() {
		estadoDispositivo.encender(this);
		intervalosDeUso.add(new Intervalo(LocalDateTime.now(), null));
	}

	public void ponerModoAhorro() {
		estadoDispositivo.ponerModoAhorro(this);
	}

	public double consumoUltimasNHoras(long horas) {

		Intervalo ultimasNHoras = new Intervalo(LocalDateTime.now().minusHours(horas), LocalDateTime.now());

		List<Intervalo> intervalosDentro = intervalosDeUso.stream().filter(i -> i.caeDentroDe(ultimasNHoras))
				.collect(Collectors.toList());

		if (intervalosDentro.isEmpty()) {

			return 0;
		}

		else

			return intervalosDentro.stream().mapToDouble(i -> i.horasDentroDe(ultimasNHoras)).sum()
					* consumoEstimadoPorHora;
	}

	public void cambiarEstado(EstadoDispositivo estadoNuevo) {

		estadoDispositivo = estadoNuevo;
	}

	public void agregarIntervalo(Intervalo i) {

		intervalosDeUso.add(i);
	}

	public List<Intervalo> getIntervalosDeUso() {
		return intervalosDeUso;
	}

	// Para aumentar consumo en un 1000%
	// aumentarConsumoPor(consumoEstimadoPorHora*10); -- PARA TEST ENTREGA 3
	public void aumentarConsumoPor(double cantidad) {

		this.consumoEstimadoPorHora += cantidad;
	}

	public void reducirConsumoPor(double cantidad) {

		consumoEstimadoPorHora = Math.max(0.0, consumoEstimadoPorHora - cantidad);
	}

	public double getConsumoTotal() {

		return this.consumoParaIntervalos(intervalosDeUso);
	}

	public boolean estaEnModoAhorro() {

		return estadoDispositivo.estaEnModoAhorro();
	}

	public Intervalo getUltimoIntervalo()
	{
		/*if(this.estadoDispositivo.estaEncendido())
		{
			return this.getIntervalosDeUso().subList(0,this.getIntervalosDeUso().size()-1).stream().max(Comparator.comparing(Intervalo::getFin)).get();
		}
		else
		{
			return this.getIntervalosDeUso().stream().max(Comparator.comparing(Intervalo::getFin)).get();
		}*/
		return this.getIntervalosDeUso().stream().max(Comparator.comparing(Intervalo::getFin)).get();
	}
	public double consumoUltimoIntervalo()
	{
		return this.consumoParaIntervalo(this.getUltimoIntervalo());
	}
	public int getPuntos() {
		return 15;
	}
	public boolean esInteligente(){return true;}


	public static class DispositivoInteligenteBuilder {

		private final String nombre;
		private double consumoEstimadoPorHora;
		private String equipoConcreto;
		private TipoDispositivo tipoDispositivo;
		private List<Intervalo> intervalosDeUso;
		private EstadoDispositivo estadoDispositivo;

		public DispositivoInteligenteBuilder(String nombre) {
			this.nombre = nombre;

		}

		public DispositivoInteligenteBuilder tipoDispositivo(TipoDispositivo tipoDispositivo) {
			this.tipoDispositivo = tipoDispositivo;
			return this;
		}

		public DispositivoInteligenteBuilder intervalosDeUso(List<Intervalo> intervalosDeUso) {

			this.intervalosDeUso = intervalosDeUso;
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

		public DispositivoInteligente build() {

			this.estadoDispositivo = EstadoDispositivo.APAGADO;
			return new DispositivoInteligente(this);

		}

	}

}
