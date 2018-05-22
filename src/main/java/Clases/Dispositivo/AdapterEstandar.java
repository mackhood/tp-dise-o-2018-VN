package Clases.Dispositivo;


public interface AdapterEstandar {

    //Es redundante poner public a los metodos de interfaces
    void apagar();

    void encender();

    EstadoDispositivo estadoDispositivo();


}