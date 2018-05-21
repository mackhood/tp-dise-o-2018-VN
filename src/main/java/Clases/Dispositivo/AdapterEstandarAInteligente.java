package Clases.Dispositivo;

public class AdapterEstandarAInteligente extends DispositivoInteligente implements AdapterEstandar{
    public AdapterEstandarAInteligente(DispositivoEstandar dispositivoEstandar) {
        super(dispositivoEstandar.getNombre(), dispositivoEstandar.getIdEstandar(), dispositivoEstandar.getFabricante());
    }
}