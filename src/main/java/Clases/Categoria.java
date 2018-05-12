package Clases;

public class Categoria {

	private String nombre;
	private Integer consumo_minimo;
	private Integer consumo_maximo;
	private Double cargo_fijo;
	private Double cargo_variable;

	public Categoria(String nombre, Integer consumo_minimo, Integer consumo_maximo, Double cargo_fijo, Double cargo_variable) {
		this.nombre = nombre;
		this.consumo_minimo = consumo_minimo;
		this.consumo_maximo = consumo_maximo;
		this.cargo_fijo = cargo_fijo;
		this.cargo_variable = cargo_variable;
	}

	//boolean cumpleCondicion(Cliente cliente) { return false; }
	
	public Double getCargoFijo() { return this.cargo_fijo; }
	public Double getCargoVariable() { return this.cargo_variable; }
	
	public Double calcularCostosPara(Cliente cliente) {
		Categoria categoria = cliente.getCategoria();
		return categoria.getCargoFijo() + categoria.getCargoVariable() * cliente.consumoEnergeticoTotal();
	}
}