package domain;

public class EstimarFacturacion {
	
	public double calcularCostosDe(Cliente cliente) {
		
		Categoria categoria = cliente.getCategoria();
		
		return categoria.getCostoFijo() + cliente.consumo()*categoria.getCostoVariable();
	}
}
