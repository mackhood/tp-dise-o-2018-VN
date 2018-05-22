package Clases.Sensor;

import Clases.Regla.Regla;

public class Sensor {

    private Regla regla;
    private Medicion medicion;

    public Sensor(Regla unaRegla) {
        this.regla = unaRegla;
    }

    //notificarMedicion y tomarMedicion capaz se podria haberlo hecho todo dentro de un mismo metodo;
    public void notificarMedicion() {
        regla.recibirMedicion(medicion);
    }

    public void tomarMedicion(Medicion unaMedicion) {
        this.medicion = unaMedicion;
        this.notificarMedicion();
    }
}
