package Clases;

public class AdapterEstandarAInteligente extends DispositivoInteligente implements AdapterEstandar {
    public AdapterEstandarAInteligente(DispositivoEstandar dispositivoEstandar) {
        super(dispositivoEstandar.nombre(), dispositivoEstandar.consumoEstimadoPorHora);
    }
}