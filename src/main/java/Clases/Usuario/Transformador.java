package Clases.Usuario;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class Transformador {

    private double posicionX;
    private double posicionY;
    private ZonaGeografica zona;
    private List<Cliente> usuariosConectados= new ArrayList<>();
    private  double energia;
    private double radio;
    private ArrayList<Double> energias = new ArrayList<Double>();
   public Transformador (int posicionX,int posicionY,ZonaGeografica zona, List<Cliente> usuariosConectados) {


       this.posicionX=posicionX;
       this.posicionY=posicionY;
       this.zona=zona;
       this.usuariosConectados=usuariosConectados;
   }




    public double calcularDistancia (Cliente cliente) {

        double a= cliente.getPosicionX();
        double b=cliente.getPosicionY();
        double z = Math.hypot(posicionX-a, posicionY-b);
        return z;
    }


public double energiaSuministrada() {

return usuariosConectados.stream().mapToDouble(usuario->usuario.consumoEnergeticoTotal()).sum();

}


    public Double getRadio() {
       return  radio;
    }
}
