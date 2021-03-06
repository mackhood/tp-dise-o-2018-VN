package datos;

import dominio.dispositivo.*;
import persistence.DispositivosManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioDispositivo implements WithGlobalEntityManager, TransactionalOps {
	
	private static RepositorioDispositivo instance = new RepositorioDispositivo();

	List<DispositivoInteligente> inteligentes = new ArrayList<>();
	List<DispositivoEstandar> estandars = new ArrayList<>();

	private RepositorioDispositivo() {
		List<Intervalo> intervalosDeUso = new ArrayList<>();
		DispositivoInteligente aireAcondicionado3500 = new DispositivoInteligente.DispositivoInteligenteBuilder(
				"Aire Acondicionado").equipoConcreto("De 3500 frigorias").consumoEstimadoPorHora(1.613)
						.intervalosDeUso(intervalosDeUso).build();
		DispositivoInteligente aireAcondicionado2200 = new DispositivoInteligente.DispositivoInteligenteBuilder(
				"Aire Acondicionado").equipoConcreto("De 2200 frigorias").consumoEstimadoPorHora(1.013)
						.intervalosDeUso(intervalosDeUso).build();

		DispositivoEstandar televisorTuboFluor21 = new DispositivoEstandar.DispositivoEstandarBuilder("Televisor")
				.equipoConcreto("Color de tubo fluorescente de 21").consumoEstimadoPorHora(0.075).build();
		DispositivoEstandar televisorTuboFluor2943 = new DispositivoEstandar.DispositivoEstandarBuilder("Televisor")
				.equipoConcreto("Color de tubo fluorescente de 29 a 34").consumoEstimadoPorHora(0.175).build();
		DispositivoEstandar televisorLCD40 = new DispositivoEstandar.DispositivoEstandarBuilder("Televisor")
				.equipoConcreto("LCD de 40").consumoEstimadoPorHora(0.18).build();
		DispositivoInteligente televisorLED24 = new DispositivoInteligente.DispositivoInteligenteBuilder("Televisor")
				.equipoConcreto("LED de 24").consumoEstimadoPorHora(0.04).intervalosDeUso(intervalosDeUso).build();
		DispositivoInteligente televisorLED32 = new DispositivoInteligente.DispositivoInteligenteBuilder("Televisor")
				.equipoConcreto("LED de 32").consumoEstimadoPorHora(0.055).intervalosDeUso(intervalosDeUso).build();
		DispositivoInteligente televisorLED40 = new DispositivoInteligente.DispositivoInteligenteBuilder("Televisor")
				.equipoConcreto("LED de 40").consumoEstimadoPorHora(0.08).intervalosDeUso(intervalosDeUso).build();

		DispositivoInteligente heladeraConFreezer = new DispositivoInteligente.DispositivoInteligenteBuilder("Heladera")
				.equipoConcreto("Con freezer").consumoEstimadoPorHora(0.09).intervalosDeUso(intervalosDeUso).build();
		DispositivoInteligente heladeraSinFreezer = new DispositivoInteligente.DispositivoInteligenteBuilder("Heladera")
				.equipoConcreto("Sin freezer").consumoEstimadoPorHora(0.075).intervalosDeUso(intervalosDeUso).build();

		DispositivoEstandar lavarropas5kgAgua = new DispositivoEstandar.DispositivoEstandarBuilder("Lavarropas")
				.equipoConcreto("Automatico de 5kg con calentamiento de agua").consumoEstimadoPorHora(0.875).build();
		DispositivoInteligente lavarropas5kg = new DispositivoInteligente.DispositivoInteligenteBuilder("Lavarropas")
				.equipoConcreto("Automatico de 5kg").consumoEstimadoPorHora(0.175).intervalosDeUso(intervalosDeUso)
				.build();
		DispositivoEstandar lavarropas5kgSemiautomatico = new DispositivoEstandar.DispositivoEstandarBuilder(
				"Lavarropas").equipoConcreto("Semi-automatico de 5kg").consumoEstimadoPorHora(0.1275).build();

		DispositivoEstandar ventiladorDePie = new DispositivoEstandar.DispositivoEstandarBuilder("Ventilador")
				.equipoConcreto("De pie").consumoEstimadoPorHora(0.09).build();
		DispositivoInteligente ventiladorDeTecho = new DispositivoInteligente.DispositivoInteligenteBuilder(
				"Ventilador").equipoConcreto("De techo").consumoEstimadoPorHora(0.06).intervalosDeUso(intervalosDeUso)
						.build();

		DispositivoInteligente lamparaHalogena40W = new DispositivoInteligente.DispositivoInteligenteBuilder("Lampara")
				.equipoConcreto("Halogena de 40W").consumoEstimadoPorHora(0.04).intervalosDeUso(intervalosDeUso)
				.build();
		DispositivoInteligente lamparaHalogena60W = new DispositivoInteligente.DispositivoInteligenteBuilder("Lampara")
				.equipoConcreto("Halogena de 60W").consumoEstimadoPorHora(0.06).intervalosDeUso(intervalosDeUso)
				.build();
		DispositivoInteligente lamparaHalogena100W = new DispositivoInteligente.DispositivoInteligenteBuilder("Lampara")
				.equipoConcreto("Halogena de 100W").consumoEstimadoPorHora(0.015).intervalosDeUso(intervalosDeUso)
				.build();
		DispositivoInteligente lampara11W = new DispositivoInteligente.DispositivoInteligenteBuilder("Lampara")
				.equipoConcreto("De 11W").consumoEstimadoPorHora(0.011).intervalosDeUso(intervalosDeUso).build();
		DispositivoInteligente lampara15W = new DispositivoInteligente.DispositivoInteligenteBuilder("Lampara")
				.equipoConcreto("De 15W").consumoEstimadoPorHora(0.015).intervalosDeUso(intervalosDeUso).build();
		DispositivoInteligente lampara20W = new DispositivoInteligente.DispositivoInteligenteBuilder("Lampara")
				.equipoConcreto("De 20W").consumoEstimadoPorHora(0.02).intervalosDeUso(intervalosDeUso).build();

		DispositivoInteligente pc = new DispositivoInteligente.DispositivoInteligenteBuilder("Computadora")
				.equipoConcreto("De escritorio").consumoEstimadoPorHora(0.4).intervalosDeUso(intervalosDeUso).build();

		DispositivoEstandar microondas = new DispositivoEstandar.DispositivoEstandarBuilder("Microondas")
				.equipoConcreto("Convencional").consumoEstimadoPorHora(0.64).build();
		DispositivoEstandar plancha = new DispositivoEstandar.DispositivoEstandarBuilder("Plancha")
				.equipoConcreto("A vapor").consumoEstimadoPorHora(0.75).build();

		inteligentes.add(aireAcondicionado3500);
		inteligentes.add(aireAcondicionado2200);
		estandars.add(televisorTuboFluor21);
		estandars.add(televisorTuboFluor2943);
		estandars.add(televisorLCD40);
		inteligentes.add(televisorLED24);
		inteligentes.add(televisorLED32);
		inteligentes.add(televisorLED40);
		inteligentes.add(heladeraConFreezer);
		inteligentes.add(heladeraSinFreezer);
		estandars.add(lavarropas5kgAgua);
		inteligentes.add(lavarropas5kg);
		estandars.add(lavarropas5kgSemiautomatico);
		estandars.add(ventiladorDePie);
		inteligentes.add(ventiladorDeTecho);
		inteligentes.add(lamparaHalogena40W);
		inteligentes.add(lamparaHalogena60W);
		inteligentes.add(lamparaHalogena100W);
		inteligentes.add(lampara11W);
		inteligentes.add(lampara15W);
		inteligentes.add(lampara20W);
		inteligentes.add(pc);
		estandars.add(microondas);
		estandars.add(plancha);

		this.asociarTipo();
	}

	public static RepositorioDispositivo getInstance() {
		return instance;
	}

	public List<Dispositivo> getTodosLosDispositivos() {

		List<Dispositivo> todos = new ArrayList<>();
		todos.addAll(inteligentes);
		todos.addAll(estandars);
		return todos;
	}

	public List<DispositivoInteligente> getInteligentes() {
		return inteligentes;
	}

	public List<DispositivoEstandar> getEstandars() {
		return estandars;
	}

	public DispositivoInteligente traerDispositivoInteligenteDeNombreConcreto(String nombre, String equipoConcreto) {
		return inteligentes.stream().filter(dispositivo -> nombre.equals(dispositivo.getNombre())
				&& equipoConcreto.equals(dispositivo.getEquipoConcreto())).collect(Collectors.toList()).get(0);
	}

	public DispositivoEstandar traerDispositivoEstandarDeNombreConcreto(String nombre, String equipoConcreto) {
		return estandars.stream().filter(dispositivo -> nombre.equals(dispositivo.getNombre())
				&& equipoConcreto.equals(dispositivo.getEquipoConcreto())).collect(Collectors.toList()).get(0);
	}

	public void asociarTipo() {
		getTodosLosDispositivos().stream()
				.forEach(d -> d.setTipo(DispositivosManager.getInstance().getTipoPorNombre(d.getNombre())));
	}

	public Dispositivo dispBuscadoDelRepositorio(Dispositivo dispositivo) {
		return this.getTodosLosDispositivos().stream()
				.filter(disp -> dispositivo.getEquipoConcreto().equals(disp.getEquipoConcreto())
						&& dispositivo.getNombre().equals(disp.getNombre()))
				.collect(Collectors.toList()).get(0);
	}

	public double coefConsumoKwhDispositivo(Dispositivo dispositivoDelCliente) {
		return this.dispBuscadoDelRepositorio(dispositivoDelCliente).getConsumoEstimadoPorHora();
	}

	public void persistirDispositivosDelRepositorio() {
		withTransaction(() -> {
			this.getEstandars().stream()
					.forEach(dispositivoEstandar -> entityManager().persist(dispositivoEstandar));
			this.getInteligentes().stream()
					.forEach(dispositivoInteligente -> entityManager().persist(dispositivoInteligente));

			entityManager().getTransaction().commit();
		});
	}

}
