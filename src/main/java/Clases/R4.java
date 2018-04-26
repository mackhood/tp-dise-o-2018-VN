package Clases;

public class R4 implements Categoria {

	double cargoFijo = 71.74;
	double cargoVariable = 0.738;
	
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumo() > 400 && cliente.consumo() <= 450;
	}

	public double getCostoFijo() {
		
		return cargoFijo;
	}

	public double getCostoVariable() {
		
		return cargoVariable;
	}
}
