package Clases;

public class AdapterEstandarAInteligente implements IEstandar
{
    DispositivoInteligente dispInteligente;

    public AdapterEstandarAInteligente(DispositivoEstandar dispositivoEstandar)
    {
        dispInteligente = new DispositivoInteligente(dispositivoEstandar.nombre(), dispositivoEstandar.estimacionConsumo());
    }
    public void encender()
    {
        dispInteligente.encender();
    }
    public void apagar()
    {
        dispInteligente.apagar();
    }
    public EstadoDispositivo estadoDispositivo()
    {
        return dispInteligente.estadoDispositivo();
    }
    public double getConsumoTotal(){return dispInteligente.getConsumoTotal();}


}
