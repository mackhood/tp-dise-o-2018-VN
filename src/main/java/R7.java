package domain;

public class R7 implements Categoria {

	double cargoFijo = 443.59;
	double cargoVariable = 0.851;
	
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumo() > 600 && cliente.consumo() <= 700;	
	}

	public double getCostoFijo() {
		
		return cargoFijo;
	}

	public double getCostoVariable() {
		
		return cargoVariable;
	}

}