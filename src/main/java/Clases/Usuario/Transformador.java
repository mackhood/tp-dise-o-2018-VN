package Clases.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Transformador {

    private int posicionX;
    private int posicionY;
    private ZonaGeografica zona;
    private List<Cliente> usuariosConectados= new ArrayList<>();
    private  double energia;
    private ArrayList<Double> energias = new ArrayList<Double>();
   public Transformador (int posicionX,int posicionY,ZonaGeografica zona, List<Cliente> usuariosConectados) {


       this.posicionX=posicionX;
       this.posicionY=posicionY;
       this.zona=zona;
       this.usuariosConectados=usuariosConectados;
   }



   



public double energiaSuministrada() {

return usuariosConectados.stream().mapToDouble(usuario->usuario.consumoEnergeticoTotal()).sum();

}


}
