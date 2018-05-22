package Clases.Dispositivo;

public class DispositivoEstandarInteligente extends DispositivoInteligente {
    DispositivoEstandar dispositivoEstandar;

    public DispositivoEstandarInteligente(DispositivoEstandar estandarATransformar)
    {
        super(estandarATransformar.getNombre(),estandarATransformar.consumoEstimadoPorHora(),estandarATransformar.getFabricante());
    }

}
