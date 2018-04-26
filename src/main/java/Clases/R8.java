package Clases;

public class R8 implements Categoria {

	double cargoFijo = 545.96;
	double cargoVariable = 0.851;
	
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumo() > 700 && cliente.consumo() <= 1400;
	}

	public double getCostoFijo() {
		
		return cargoFijo;
	}

	public double getCostoVariable() {
		
		return cargoVariable;
	}

}