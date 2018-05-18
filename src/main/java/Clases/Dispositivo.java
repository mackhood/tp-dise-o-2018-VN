package Clases;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public abstract class Dispositivo {

	String nombre;
	EstadoDispositivo estado = null;
	double consumoEstimadoPorHora;
	double horasDeUso = 0;

	public Dispositivo(String nombre, double consumoEstimadoPorHora) {
		this.nombre = nombre;
		this.consumoEstimadoPorHora = consumoEstimadoPorHora;
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

	public double consumoEstimadoPorHora()
	{
		return consumoEstimadoPorHora;
	}
	public String nombre() {
		
		return nombre;
	}

	public double getConsumoTotal() {
		
		return consumoEstimadoPorHora * horasDeUso;
	}

	public EstadoDispositivo estadoDispositivo() {
		
		return estado;
	}

	public boolean esCiertoEstado(EstadoDispositivo estadoCond)
	{
		return estado.equals(estadoCond);
	}

    public void aumentarConsumoPor(int aumento)
	{
		consumoEstimadoPorHora = consumoEstimadoPorHora + aumento;
	}

}
