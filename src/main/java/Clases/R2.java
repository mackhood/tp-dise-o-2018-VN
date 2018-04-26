package Clases;

public class R2 extends Categoria {

	double cargoFijo = 35.32;
	double cargoVariable = 0.644;
	
	@Override
	public boolean cumpleCondicion(Cliente cliente) {
		
		return cliente.consumoEnergeticoTotal() > 150 && cliente.consumoEnergeticoTotal() <= 325;
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
