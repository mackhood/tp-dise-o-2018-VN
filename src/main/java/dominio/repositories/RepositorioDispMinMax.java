package dominio.repositories;

import dominio.dispositivo.Dispositivo;
import dominio.dispositivo.DispositivoInteligente;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioDispMinMax {
	List<Dispositivo> dispositivos = new ArrayList<>();
	double[] coefsRestriccion;

	public RepositorioDispMinMax() {
		DispositivoInteligente aireAcondicionado = new DispositivoInteligente.DispositivoInteligenteBuilder("aireAcondicionado").restriccionMinima((double) 90).restriccionMinima((double) 360).build();
		DispositivoInteligente lampara = new DispositivoInteligente.DispositivoInteligenteBuilder("lampara").restriccionMinima((double) 90).restriccionMinima((double) 360).build();
		DispositivoInteligente televisor = new DispositivoInteligente.DispositivoInteligenteBuilder("televisor").restriccionMinima((double) 90).restriccionMinima((double) 360).build();
		DispositivoInteligente computadora = new DispositivoInteligente.DispositivoInteligenteBuilder("computadora").restriccionMinima((double) 60).restriccionMinima((double) 360).build();
		DispositivoInteligente lavarropas = new DispositivoInteligente.DispositivoInteligenteBuilder("lavarropas").restriccionMinima((double) 6).restriccionMinima((double) 30).build();
		DispositivoInteligente microondas = new DispositivoInteligente.DispositivoInteligenteBuilder("microondas").restriccionMinima((double) 3).restriccionMinima((double) 15).build();
		DispositivoInteligente plancha = new DispositivoInteligente.DispositivoInteligenteBuilder("plancha").restriccionMinima((double) 3).restriccionMinima((double) 30).build();
		DispositivoInteligente ventilador = new DispositivoInteligente.DispositivoInteligenteBuilder("ventilador").restriccionMinima((double) 120).restriccionMinima((double) 360).build();


		dispositivos.add(aireAcondicionado);
		dispositivos.add(lampara);
		dispositivos.add(televisor);
		dispositivos.add(computadora);
		dispositivos.add(lavarropas);
		dispositivos.add(microondas);
		dispositivos.add(plancha);
		dispositivos.add(ventilador);
		coefsRestriccion = new double[dispositivos.size()];
	}

	public void agregarCerosAlArray() {
		for (int i = 0; i < dispositivos.size(); i++) {
			coefsRestriccion[i] = 0;
		}
	}

	public void modificarElemArray(int index) {
		coefsRestriccion[index] = 1;
	}

	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}

	public Dispositivo buscarDispositivo(Dispositivo dispBusq) {
		return this.getDispositivos().stream().filter(disp -> dispBusq.getNombre() == disp.getNombre())
				.collect(Collectors.toList()).get(0);
	}

	public double restriccionMinima(Dispositivo dispositivo) {
		return this.buscarDispositivo(dispositivo).getRestriccionMinima();
	}

	public double restriccionMaxima(Dispositivo dispositivo) {
		return this.buscarDispositivo(dispositivo).getRestriccionMaxima();
	}

	public int posicionDispositivoBusq(Dispositivo dispositivo) {
		return this.getDispositivos().indexOf(this.buscarDispositivo(dispositivo));
	}

	public double[] coefsResctriccionDeUnDispositivo(Dispositivo dispositivo) {
		this.agregarCerosAlArray();
		this.modificarElemArray(this.posicionDispositivoBusq(dispositivo));
		return coefsRestriccion;
	}
	/*
	 * public double[] coefsFuncionEconomica(List<dispositivo> dispositivos) {
	 * double[] coefsFuncionEco = new double(dispositivos.size()); }
	 */
}
