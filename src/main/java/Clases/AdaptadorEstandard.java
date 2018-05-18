package Clases;

public interface AdaptadorEstandard {

    //Es redundante poner public a los metodos de interfaces
    void apagar();

    void encender();

    EstadoDispositivoEnum estadoDispositivo();

    double getConsumoTotal();

}
