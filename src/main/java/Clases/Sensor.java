package Clases;

public abstract class Sensor {
    String medicion;

    public void comunicarMedicion(Regla unaRegla) {
        unaRegla.recibirMedicion(medicion);
    }

    public boolean mismaMedicion(String otraMedicion) {
        return medicion.equals(otraMedicion);
    }

    public String medicion() {
        return medicion;
    }
}
