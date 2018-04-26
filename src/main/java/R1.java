package domain;

public class R1 implements Categoria {
	
	double cargoFijo = 18.76;
	double cargoVariable = 0.644;
	
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumo() < 150;
	}
	
	public double getCostoFijo() {
		
		return cargoFijo;
	}

	public double getCostoVariable() {
		
		return cargoVariable;
	}

}
