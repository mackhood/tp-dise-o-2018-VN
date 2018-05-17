package Clases;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public abstract class Dispositivo {

	String nombre;
	EstadoDispositivo estado;
	double estimacionConsumo;
	double horasDeUso = 0;

	public Dispositivo(String nombre, double consumo) {
		this.nombre = nombre;
		this.estimacionConsumo = consumo;
	}
	public void serUsado(int cantHorasEstimativa)
	{
		horasDeUso = horasDeUso + cantHorasEstimativa;
	}
	public void encender()
	{
		estado = EstadoDispositivo.ENCENDIDO;
	}

	public void apagar()
	{
		estado = EstadoDispositivo.APAGADO;
	}

	public double estimacionConsumo()
	{
		return estimacionConsumo;
	}
	public String nombre() {
		
		return nombre;
	}

	public double getConsumoTotal() {
		
		return estimacionConsumo * horasDeUso;
	}

	public EstadoDispositivo estadoDispositivo() {
		
		return estado;
	}
	
}
