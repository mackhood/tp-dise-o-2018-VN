package domain;

public class R6 implements Categoria {

	double cargoFijo =  220.75;
	double cargoVariable = 0.832;
	
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumo() > 500 && cliente.consumo() <= 600;
	}

	public double getCostoFijo() {
		
		return cargoFijo;
	}

	public double getCostoVariable() {
		
		return cargoVariable;
	}

}
