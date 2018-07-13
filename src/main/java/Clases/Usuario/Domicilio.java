package Clases.Usuario;

import Clases.ZonaGeografica.ZonaGeografica;

public class Domicilio {

    public String calle;
    public int altura;
    public int piso;
    public char departamento;
    public ZonaGeografica zona;
    
    public Domicilio(String calle, int altura, int piso, char departamento) {
        this.calle = calle;
        this.altura = altura;
        this.piso = piso;
        this.departamento = departamento;
    	//this.zona = zona;
    }
    

}
