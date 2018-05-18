package Clases;

public abstract class Actuador {
    //Cada uno de los distintos Actuadores va a tener su propia logica a implementar en ejecutar
    DispositivoInteligente dispositivo;
    Fabricante fabricante;

    public boolean puedeEjecutarse() {
        return fabricante.verificarCoincidenciaIDS();
    }

    public void ejecutar() {
    }

    public Actuador(DispositivoInteligente unDispositivo, Fabricante unFabricante) {
        this.dispositivo = unDispositivo;
        this.fabricante = unFabricante;
    }

}
