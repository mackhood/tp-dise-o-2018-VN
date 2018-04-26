package Clases;

public class Categoria {
	
	boolean cumpleCondicion(Cliente cliente) { return false; }
	
	double getCostoFijo() { return 0; }
	
	double getCostoVariable() { return 0; }
	
	double calcularCostosPara(Cliente cliente) {
		
		Categoria categoria = cliente.getCategoria();
		return categoria.getCostoFijo() + categoria.getCostoVariable()* cliente.consumoEnergeticoTotal();	
	}
}