package Clases;

public class AdapterEstandarAInteligente extends DispositivoInteligente
{
    public AdapterEstandarAInteligente(DispositivoEstandar dispositivoEstandar)
    {
        super(dispositivoEstandar.nombre(), dispositivoEstandar.consumoEstimadoPorHora);
    }
}
