package Clases;

public class AdapterEstandarAInteligente extends DispositivoInteligente implements AdaptadorEstandard {
    public AdapterEstandarAInteligente(DispositivoEstandar dispositivoEstandar) {
        super(dispositivoEstandar.nombre(), dispositivoEstandar.consumoEstimadoPorHora);
    }
}
