package dominio.regla;

public class ReglaNoCumplida implements EstadoRegla {

    public ReglaNoCumplida() {
    }

    /*public void cambiarEstado(regla unaRegla) {
        unaRegla.cambiarEstado(new ReglaCumplida());
    }*/

    public boolean cumpleCondiciones() {
        return false;
    }
}
