package Clases.Sensor;

public class MedicionTemperatura extends Medicion {
    public MedicionTemperatura(boolean estado) {
        this.estado = estado;
    }

    @Override
    public boolean mismoTipoDeMedicion(Medicion otraMedicion) {
        return otraMedicion instanceof MedicionTemperatura;
    }
}