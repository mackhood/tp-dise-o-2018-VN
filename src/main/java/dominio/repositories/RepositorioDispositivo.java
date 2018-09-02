package dominio.repositories;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoEstandar;
import dominio.dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDispositivo {
	// public static RepositorioDispositivo instance;
	List<Dispositivo> dispositivos = new ArrayList<>();

	public RepositorioDispositivo() {
		DispositivoInteligente aireAcondicionado3500 = new DispositivoInteligente.DispositivoInteligenteBuilder("aireAcondicionado").equipoConcreto("De 3500 frigorias").esBajoConsumo(false).consumoEstimadoPorHora(1.613).restriccionMinima((double)90).restriccionMaxima((double)360).build();
		DispositivoInteligente aireAcondicionado2200 = new DispositivoInteligente.DispositivoInteligenteBuilder("aireAcondicionado").equipoConcreto("De 2200 frigorias").esBajoConsumo(true).consumoEstimadoPorHora(1.013).restriccionMinima((double) 90).restriccionMaxima((double) 360).build();
		DispositivoEstandar televisorTuboFluor21 = new DispositivoEstandar.DispositivoEstandarBuilder("televisor")
				.equipoConcreto("Color de tubo fluorescente de 21").esBajoConsumo(false).consumoEstimadoPorHora(0.075)
				.restriccionMinima((double) 90).restriccionMaxima((double) 360).build();
		DispositivoEstandar televisorTuboFluor2943 = new DispositivoEstandar.DispositivoEstandarBuilder("televisor")
				.equipoConcreto("Color de tubo fluorescente de 29 a 34").esBajoConsumo(false)
				.consumoEstimadoPorHora(0.175).restriccionMinima((double) 90).restriccionMaxima((double) 360).build();
		DispositivoEstandar televisorLCD40 = new DispositivoEstandar.DispositivoEstandarBuilder("televisor")
				.equipoConcreto("LCD de 40").esBajoConsumo(false).consumoEstimadoPorHora(0.18)
				.restriccionMinima((double) 90).restriccionMaxima((double) 360).build();
		DispositivoInteligente televisorLED24 = new DispositivoInteligente.DispositivoInteligenteBuilder("televisor").equipoConcreto("LED de 24").esBajoConsumo(true).consumoEstimadoPorHora(0.04).restriccionMinima((double) 90).restriccionMaxima((double) 360).build();
		DispositivoInteligente televisorLED32 = new DispositivoInteligente.DispositivoInteligenteBuilder("televisor").equipoConcreto("LED de 32").esBajoConsumo(true).consumoEstimadoPorHora(0.055).restriccionMinima((double) 90).restriccionMaxima((double) 360).build();
		DispositivoInteligente televisorLED40 = new DispositivoInteligente.DispositivoInteligenteBuilder("televisor").equipoConcreto("LED de 40").esBajoConsumo(true).consumoEstimadoPorHora(0.08).restriccionMinima((double) 90).restriccionMaxima((double) 360).build();
		DispositivoInteligente heladeraConFreezer = new DispositivoInteligente.DispositivoInteligenteBuilder("heladera").equipoConcreto("Con freezer").esBajoConsumo(true).consumoEstimadoPorHora(0.09).build();
		DispositivoInteligente heladeraSinFreezer = new DispositivoInteligente.DispositivoInteligenteBuilder("heladera").equipoConcreto("Sin freezer").esBajoConsumo(true).consumoEstimadoPorHora(0.075).build();



		DispositivoEstandar lavarropas5kgAgua = new DispositivoEstandar.DispositivoEstandarBuilder("lavarropas")
				.equipoConcreto("Automatico de 5kg con calentamiento de agua").esBajoConsumo(false)
				.consumoEstimadoPorHora(0.875).restriccionMinima((double) 6).restriccionMaxima((double) 30).build();
		DispositivoInteligente lavarropas5kg = new DispositivoInteligente.DispositivoInteligenteBuilder("lavarropas").equipoConcreto("Automatico de 5kg").esBajoConsumo(true).consumoEstimadoPorHora(0.175).restriccionMinima((double)6).restriccionMaxima((double)30).build();

		DispositivoEstandar lavarropas5kgSemiautomatico = new DispositivoEstandar.DispositivoEstandarBuilder(
				"lavarropas").equipoConcreto("Semi-automatico de 5kg").esBajoConsumo(true)
						.consumoEstimadoPorHora(0.1275).restriccionMinima((double) 6).restriccionMaxima((double) 30)
						.build();

		DispositivoEstandar ventiladorDePie = new DispositivoEstandar.DispositivoEstandarBuilder("ventilador")
				.equipoConcreto("De pie").esBajoConsumo(true).consumoEstimadoPorHora(0.09)
				.restriccionMinima((double) 120).restriccionMaxima((double) 360).build();

		DispositivoInteligente ventiladorDeTecho = new DispositivoInteligente.DispositivoInteligenteBuilder("ventilador").equipoConcreto("De techo").esBajoConsumo(true).consumoEstimadoPorHora(0.06).restriccionMinima((double)120).restriccionMaxima((double)360).build();

		DispositivoInteligente lamparaHalogena40W = new DispositivoInteligente.DispositivoInteligenteBuilder("lampara").equipoConcreto("Halogena de 40W").esBajoConsumo(false).consumoEstimadoPorHora(0.04).restriccionMinima((double)90).restriccionMaxima((double)360).build();
		DispositivoInteligente lamparaHalogena60W = new DispositivoInteligente.DispositivoInteligenteBuilder("lampara").equipoConcreto("Halogena de 60W").esBajoConsumo(false).consumoEstimadoPorHora(0.06).restriccionMinima((double)90).restriccionMaxima((double)360).build();
		DispositivoInteligente lamparaHalogena100W = new DispositivoInteligente.DispositivoInteligenteBuilder("lampara").equipoConcreto("Halogena de 100W").esBajoConsumo(false).consumoEstimadoPorHora(0.015).restriccionMinima((double)90).restriccionMaxima((double)360).build();
		DispositivoInteligente lampara11W = new DispositivoInteligente.DispositivoInteligenteBuilder("lampara").equipoConcreto("De 11W").esBajoConsumo(true).consumoEstimadoPorHora(0.011).restriccionMinima((double)90).restriccionMaxima((double)360).build();
		DispositivoInteligente lampara15W = new DispositivoInteligente.DispositivoInteligenteBuilder("lampara").equipoConcreto("De 15W").esBajoConsumo(true).consumoEstimadoPorHora(0.015).restriccionMinima((double)90).restriccionMaxima((double)360).build();
		DispositivoInteligente lampara20W = new DispositivoInteligente.DispositivoInteligenteBuilder("lampara").equipoConcreto("De 20W").esBajoConsumo(true).consumoEstimadoPorHora(0.02).restriccionMinima((double)90).restriccionMaxima((double)360).build();

		DispositivoInteligente pc = new DispositivoInteligente.DispositivoInteligenteBuilder("PC").equipoConcreto("De escritorio").esBajoConsumo(true).consumoEstimadoPorHora(0.4).restriccionMinima((double)60).restriccionMaxima((double)360).build();


		DispositivoEstandar microondas = new DispositivoEstandar.DispositivoEstandarBuilder("microondas")
				.equipoConcreto("Convencional").esBajoConsumo(true).consumoEstimadoPorHora(0.64)
				.restriccionMinima((double) 3).restriccionMaxima((double) 15).build();
		DispositivoEstandar plancha = new DispositivoEstandar.DispositivoEstandarBuilder("plancha")
				.equipoConcreto("A vapor").esBajoConsumo(true).consumoEstimadoPorHora(0.75)
				.restriccionMinima((double) 3).restriccionMaxima((double) 30).build();

		dispositivos.add(aireAcondicionado3500);
		dispositivos.add(aireAcondicionado2200);
		dispositivos.add(televisorTuboFluor21);
		dispositivos.add(televisorTuboFluor2943);
		dispositivos.add(televisorLCD40);
		dispositivos.add(televisorLED24);
		dispositivos.add(televisorLED32);
		dispositivos.add(televisorLED40);
		dispositivos.add(heladeraConFreezer);
		dispositivos.add(heladeraSinFreezer);
		dispositivos.add(lavarropas5kgAgua);
		dispositivos.add(lavarropas5kg);
		dispositivos.add(lavarropas5kgSemiautomatico);
		dispositivos.add(ventiladorDePie);
		dispositivos.add(ventiladorDeTecho);
		dispositivos.add(lamparaHalogena40W);
		dispositivos.add(lamparaHalogena60W);
		dispositivos.add(lamparaHalogena100W);
		dispositivos.add(lampara11W);
		dispositivos.add(lampara15W);
		dispositivos.add(lampara20W);
		dispositivos.add(pc);
		dispositivos.add(microondas);
		dispositivos.add(plancha);

	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public Dispositivo dispBuscado(Dispositivo dispositivo) {
		return this.getDispositivos().stream()
				.filter(disp -> dispositivo.getEquipoConcreto() == disp.getEquipoConcreto())
				.collect(Collectors.toList()).get(0);
	}

	public double coefConsumokwh(Dispositivo dispositivo) {
		return this.dispBuscado(dispositivo).getConsumoEstimadoPorHora();
	}

	public double[] coeficientesDeConsumoKwh(List<Dispositivo> dispositivos) {
		List<Double> listaCoeficientes = dispositivos.stream().map(disp -> this.coefConsumokwh(disp))
				.collect(Collectors.toList());
		double[] arrayCoef = new double[listaCoeficientes.size()];
		for (int i = 0; i < listaCoeficientes.size(); i++)
			arrayCoef[i] = listaCoeficientes.get(i);

		return arrayCoef;
	}

	public double[] coeficientesVariables(List<Dispositivo> dispositivos) {
		List<Double> listaCoeficientes = dispositivos.stream().map(disp -> this.coefConsumokwh(disp))
				.collect(Collectors.toList());
		double[] arrayCoef = new double[listaCoeficientes.size()];
		for (int i = 0; i < listaCoeficientes.size(); i++)
			arrayCoef[i] = 1;

		return arrayCoef;
	}
}
