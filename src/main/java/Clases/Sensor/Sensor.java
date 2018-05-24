package Clases.Sensor;

import Clases.Regla.Regla;

public class Sensor {

    private Regla regla;
    private Medicion medicion;

    public Sensor(Regla unaRegla) {
        this.regla = unaRegla;
    }

    public void notificarMedicion() {
        regla.recibirMedicion(medicion);
    }

    private void tomarMedicion(MedioExterno unaMedicion) {
        this.medicion = unaMedicion;
		this.notificarMedicion();
    }
}
