package Clases.Sensor;

public abstract class Medicion {
    boolean estado;

    public abstract boolean mismoTipoDeMedicion(Medicion otraMedicion);

    public boolean getEstado() {
        return estado;
    }

    public boolean mismoEstado(Medicion otraMedicion) {
        return estado == otraMedicion.getEstado();
    }

    public boolean compararMedicion(Medicion otraMedicion){
        return this.mismoTipoDeMedicion(otraMedicion) && this.mismoEstado(otraMedicion);
    }
}
