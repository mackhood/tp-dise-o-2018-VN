package domain;

public class R5 implements Categoria {

	double cargoFijo = 110.38;
	double cargoVariable = 0.794;
	
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumo() > 450 && cliente.consumo() <= 500;
	}

	public double getCostoFijo() {
		
		return cargoFijo;
	}

	public double getCostoVariable() {
		
		return cargoVariable;
	}

}
