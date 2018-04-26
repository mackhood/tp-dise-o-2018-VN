package Clases;

public class R2 implements Categoria {

	double cargoFijo = 35.32;
	double cargoVariable = 0.644;
	
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumo() > 150 && cliente.consumo() <= 325;
	}

	public double getCostoFijo() {
		
		return cargoFijo;
	}

	public double getCostoVariable() {
		
		return cargoVariable;
	}

}
