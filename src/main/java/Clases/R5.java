package Clases;

public class R5 extends Categoria {

	double cargoFijo = 110.38;
	double cargoVariable = 0.794;
	
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumoEnergeticoTotal() > 450 && cliente.consumoEnergeticoTotal() <= 500;
	}

	public double getCostoFijo() {
		
		return cargoFijo;
	}

	public double getCostoVariable() {
		
		return cargoVariable;
	}

}
