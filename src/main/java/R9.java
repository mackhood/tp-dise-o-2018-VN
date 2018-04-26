package domain;

public class R9 implements Categoria {

	double cargoFijo = 887.19;
	double cargoVariable = 0.851;
	
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumo() > 1400;
	}

	public double getCostoFijo() {
		
		return cargoFijo;
	}

	public double getCostoVariable() {
		
		return cargoVariable;
	}

}