package Clases;

public class Domicilio {

	String calle;
	int altura;
	int piso;
	char departamento;
	
	Domicilio(String calle,int altura) { this.calle = calle; this.altura = altura; }
	
	Domicilio(String calle,int altura,int piso, char departamento) {
		
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
	}
}
