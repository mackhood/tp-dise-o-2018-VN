package Clases;

public class R7 extends Categoria {

	double cargoFijo = 443.59;
	double cargoVariable = 0.851;
	
	@Override
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumoEnergeticoTotal() > 600 && cliente.consumoEnergeticoTotal() <= 700;	
	}

	@Override
	public double getCostoFijo() {
		
		return cargoFijo;
	}

	@Override
	public double getCostoVariable() {
		
		return cargoVariable;
	}

}