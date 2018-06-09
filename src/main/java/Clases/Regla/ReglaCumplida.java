package Clases.Regla;

public class ReglaCumplida implements EstadoRegla {

    public ReglaCumplida() {
    }


    /*public void cambiarEstado(Regla unaRegla) {
        unaRegla.cambiarEstado(new ReglaNoCumplida());
    }*/

    public boolean cumpleCondiciones() {
        return true;
    }
}
