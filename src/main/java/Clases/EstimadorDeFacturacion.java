package Clases;

public class EstimadorDeFacturacion {
	
	public double calcularCostosDe(Cliente cliente) {
		
		Categoria categoria = cliente.getCategoria();
		
		return categoria.getCostoFijo() + cliente.consumo()*categoria.getCostoVariable();
	}
}
