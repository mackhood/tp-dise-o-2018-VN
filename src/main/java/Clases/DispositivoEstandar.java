package Clases;

public class DispositivoEstandar extends Dispositivo
{
    AdapterEstandarAInteligente adapter;
    public DispositivoEstandar(String nombre, double consumo)
    {
        super(nombre,consumo);
    }
    public boolean tieneAdapter()
    {
        return adapter!=NULL;
    }
    public void agregarAdaptadorInteligente()
    {
        adapter = new AdapterEstandarAInteligente(this);
    }

    @Override
    public void apagar()
    {
        if(tieneAdapter())
        {
            adapter.apagar();
        }
    }

    @Override
    public void encender()
    {
        if(tieneAdapter())
        {
            adapter.encender();
        }
    }

    @Override
    public EstadoDispositivo estadoDispositivo()
    {
        if(tieneAdapter())
        {
            adapter.estadoDispositivo();
        }
    }

}
