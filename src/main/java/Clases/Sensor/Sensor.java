package Clases.Sensor;

import Clases.Regla.Regla;

public class Sensor {

    private Regla regla;

    public Sensor(Regla unaRegla) {
        this.regla = unaRegla;
    }

    public void notificarMedicion(Medicion unaMedicion) {
        regla.recibirMedicion(unaMedicion);
    }

    public void tomarMedicion(MedioExterno unMedio) {
        Medicion medicionObtenida = unMedio.obtenerMedicion();
		this.notificarMedicion(medicionObtenida);
    }
}
