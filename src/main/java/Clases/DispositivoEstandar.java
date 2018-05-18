package Clases;

import Clases.entities.DispositivoEstandarException;
import com.sun.istack.internal.localization.NullLocalizable;

public class DispositivoEstandar extends Dispositivo
{
    AdapterEstandarAInteligente adapter = null;
    public DispositivoEstandar(String nombre, double estimacionConsumo)
    {
        super(nombre,estimacionConsumo);
    }
    public boolean tieneAdapter()
    {
        return adapter != null;
    }

    public void agregarAdaptadorInteligente()
    {
        adapter = new AdapterEstandarAInteligente(this);
    }

    public DispositivoEstandarException tirarException()
    {
        return  new DispositivoEstandarException();
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
            return adapter.estadoDispositivo();
        }
        else
        {
            return null;
        }
    }
    @Override
    public double getConsumoTotal()
    {
        if(tieneAdapter())
        {
            return adapter.getConsumoTotal();
        }
        else
        {
            return 0;
        }
    }
}
