package dominio.dispositivo;

public enum TipoDispositivo {
    AIREACONDICIONADO(90, 360),
    LAMPARA(90, 360),
    TELEVISOR(90, 360),
    LAVARROPAS(6, 30),
    COMPUTADORA(60, 360),
    MICROONDAS(3, 15),
    PLANCHA(3, 30),
    VENTILADOR(120, 360),
    OTRO(0, 0);

    double restriccionMinima;
    double restriccionMaxima;

    TipoDispositivo() {
    }

    TipoDispositivo(double restriccionMinima, double restriccionMaxima) {
        this.restriccionMinima = restriccionMinima;
        this.restriccionMaxima = restriccionMaxima;
    }

    public double getRestriccionMinima() {
        return restriccionMinima;
    }

    public double getRestriccionMaxima() {
        return restriccionMaxima;
    }

}
