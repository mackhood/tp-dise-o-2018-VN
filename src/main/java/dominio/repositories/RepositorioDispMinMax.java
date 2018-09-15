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
		DispositivoInteligente aireAcondicionado = new DispositivoInteligente.DispositivoInteligenteBuilder("aireAcondicionado").build();
		DispositivoInteligente lampara = new DispositivoInteligente.DispositivoInteligenteBuilder("lampara").build();
		DispositivoInteligente televisor = new DispositivoInteligente.DispositivoInteligenteBuilder("televisor").build();
		DispositivoInteligente computadora = new DispositivoInteligente.DispositivoInteligenteBuilder("computadora").build();
		DispositivoInteligente lavarropas = new DispositivoInteligente.DispositivoInteligenteBuilder("lavarropas").build();
		DispositivoInteligente microondas = new DispositivoInteligente.DispositivoInteligenteBuilder("microondas").build();
		DispositivoInteligente plancha = new DispositivoInteligente.DispositivoInteligenteBuilder("plancha").build();
		DispositivoInteligente ventilador = new DispositivoInteligente.DispositivoInteligenteBuilder("ventilador").build();


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
