package Clases.Sensor;

public class MedicionMovimiento extends Medicion {

    public MedicionMovimiento(boolean estado) {
        this.estado = estado;
    }

    @Override
    public boolean mismoTipoDeMedicion(Medicion otraMedicion) {
        return otraMedicion instanceof MedicionMovimiento;
    }

}
