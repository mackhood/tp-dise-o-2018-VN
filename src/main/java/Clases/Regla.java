package Clases;


public class Regla {
    Actuador actuador;
    Sensor sensor;

    public Regla(Actuador actuador, Sensor sensor) {
        this.actuador = actuador;
        this.sensor = sensor;
    }

    public void dispararActuador() {
        actuador.ejecutar();
    }

    //recibirMedicion verifica si la medicion de la regla es igual a la medicion de la que quiere comunicarse con este
    public void recibirMedicion(String medicion) {
        if (sensor.mismaMedicion(medicion)) {
            this.dispararActuador();
        }
    }
}