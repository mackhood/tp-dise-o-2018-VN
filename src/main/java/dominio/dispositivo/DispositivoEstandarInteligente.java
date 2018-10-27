package dominio.dispositivo;


public class DispositivoEstandarInteligente extends DispositivoInteligente {

    DispositivoEstandar dispositivoEstandar;


    public DispositivoEstandarInteligente(DispositivoEstandar dispositivoEstandar) {
        super();

        this.nombre = dispositivoEstandar.getNombre();
        this.consumoEstimadoPorHora = dispositivoEstandar.consumoEstimadoPorHora();
        this.dispositivoEstandar = dispositivoEstandar;
    }

    @Override
    public int getPuntos() {
        return 10;
    }
}
