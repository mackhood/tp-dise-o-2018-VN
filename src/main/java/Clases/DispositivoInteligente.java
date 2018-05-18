package Clases;

public class DispositivoInteligente extends Dispositivo {
    public DispositivoInteligente(String nombre, double estimacionConsumo) {
        super(nombre, estimacionConsumo);
    }

    public double consumoUltimasXHoras(int X) {
        if (X >= horasDeUso) {
            return consumoEstimadoPorHora * horasDeUso;
        } else {
            return consumoEstimadoPorHora * X;
        }
    }

    public void modoAhorroDeEnergia() {
        this.estado = EstadoDispositivoEnum.APAGADO.MODOAHORRO;
    }

}
