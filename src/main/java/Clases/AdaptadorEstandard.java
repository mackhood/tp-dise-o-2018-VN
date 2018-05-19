package Clases;

import Clases.EstadoDispositivo;

public interface AdaptadorEstandard {


    public void apagar();
    public void encender();
    public EstadoDispositivo estadoDispositivo();
    public double getConsumoTotal();


}
