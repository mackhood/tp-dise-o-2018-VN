package Clases.Sensor;

import Clases.Regla.Regla;

public class Condicion {
    String tipo;
    Regla regla;
    int estadoCondicion;
    int estadoSensor;

    public Condicion(Regla unaRegla, int estadoCondicion, String tipo){
        regla = unaRegla;
        this.tipo = tipo;
        this.estadoCondicion = estadoCondicion;
    }
    public boolean cumpleCondicion() {
        return estadoCondicion == estadoSensor;
    }

    public void actualizar(Sensor unSensor) {
        if(unSensor.getTipo() == tipo)
        {
            this.estadoSensor = unSensor.getEstadoSensor();


            if(regla.cumpleTodasLasCondiciones())
            {
                regla.ejecutarActuador();
            }

        }

    }
}
