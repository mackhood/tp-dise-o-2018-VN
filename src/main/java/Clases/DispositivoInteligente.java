package Clases;

public class DispositivoInteligente extends Dispositivo {
    Fabricante fabricante;
    public DispositivoInteligente(String nombre, double consumoEstimadoPorHora) {
        super(nombre,consumoEstimadoPorHora);
    }

    public int identificadorFabrica()
    {
        return fabricante.idFabrica();
    }

    public void comunicarseConFabrica(Fabricante unFabricante)
    {
        unFabricante.recibirIDDispositivo(this.identificadorFabrica());
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
