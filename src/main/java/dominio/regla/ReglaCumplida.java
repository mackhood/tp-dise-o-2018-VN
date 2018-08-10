package dominio.regla;

public class ReglaCumplida implements EstadoRegla {

    public ReglaCumplida() {
    }


    /*public void cambiarEstado(regla unaRegla) {
        unaRegla.cambiarEstado(new ReglaNoCumplida());
    }*/

    public boolean cumpleCondiciones() {
        return true;
    }
}
