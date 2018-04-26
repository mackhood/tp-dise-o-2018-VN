package domain;

public class R3 implements Categoria {

	double cargoFijo = 60.71;
	double cargoVariable = 0.681;
	
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumo() > 325 && cliente.consumo() <= 400;
	}

	public double getCostoFijo() {
		
		return cargoFijo;
	}

	public double getCostoVariable() {
		
		return cargoVariable;
	}

}
