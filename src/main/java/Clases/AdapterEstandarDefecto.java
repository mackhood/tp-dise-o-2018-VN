package Clases;

public class AdapterEstandarDefecto implements  AdapterEstandar
{
    private static AdapterEstandarDefecto ourInstance = new AdapterEstandarDefecto();

    public static AdapterEstandarDefecto getInstance() {
        return ourInstance;
    }

    public AdapterEstandarDefecto() {
    }

    public void apagar() {

    }
    public void encender(){

    }
    public EstadoDispositivoEnum estadoDispositivo(){
        return null;
    }
    public double getConsumoTotal(){
        return 0;
    }

}