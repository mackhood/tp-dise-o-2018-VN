package Clases.Regla;

public class ReglaNoCumplida implements EstadoRegla {

    public ReglaNoCumplida() {
    }

    /*public void cambiarEstado(Regla unaRegla) {
        unaRegla.cambiarEstado(new ReglaCumplida());
    }*/

    public boolean cumpleCondiciones() {
        return false;
    }
}
