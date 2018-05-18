package Clases;

public abstract class Dispositivo {

    protected String nombre;
    protected EstadoDispositivoEnum estado = null;
    protected double consumoEstimadoPorHora;
    protected double horasDeUso = 0;

    public Dispositivo(String nombre, double consumoEstimadoPorHora) {
        this.nombre = nombre;
        this.consumoEstimadoPorHora = consumoEstimadoPorHora;
    }

    public void serUsado(int cantHorasEstimativa) {
        horasDeUso = horasDeUso + cantHorasEstimativa;
    }

    public void encender() {
        estado = EstadoDispositivoEnum.ENCENDIDO;
    }
  
    public void apagar() {
        estado = EstadoDispositivoEnum.APAGADO;
    }

    public double consumoEstimadoPorHora() {
        return consumoEstimadoPorHora;
    }

    public String nombre() {

        return nombre;
    }

    public double getConsumoTotal() {

        return consumoEstimadoPorHora * horasDeUso;
    }

    public EstadoDispositivoEnum estadoDispositivo() {
        return estado;
    }

    public boolean esCiertoEstado(EstadoDispositivoEnum estadoCond) {
        return estado.equals(estadoCond);
    }

    public void aumentarConsumoPor(int aumento) {
        consumoEstimadoPorHora = consumoEstimadoPorHora + aumento;
    }

}
