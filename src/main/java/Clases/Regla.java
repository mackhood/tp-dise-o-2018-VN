package Clases;


public class Regla {
    Actuador actuador;
    Sensor sensor;

    public void dispararActuador() {
        actuador.ejecutar();
    }

    public void recibirMedicion(String medicion) {
        if (sensor.mismaMedicion(medicion))
        {
            this.dispararActuador();
        }
    }
}

