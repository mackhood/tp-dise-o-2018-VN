package Clases;
import Clases.repositories.RepositorioCategoria;




public class Categoria {
	private double consumoMinimo;
	private double consumoMaximo;
	private String nombre;
	
	private Double cargo_fijo;
	private Double cargo_variable;

	public Categoria(String nombre, Integer consumoMinimo, Integer consumoMaximo, Double cargo_fijo, Double cargo_variable) {
		this.nombre = nombre;
		this.consumoMinimo = consumoMinimo;
		this.consumoMaximo = consumoMaximo;
		this.cargo_fijo = cargo_fijo;
		this.cargo_variable = cargo_variable;
	}

  public double getConsumoMinimo () {
	  
	  return this.consumoMinimo;
  }
	
public double getConsumoMaximo () {
	  
	  return this.consumoMaximo;
  } 
	
	
	
public boolean cumpleCondicion(Cliente cliente) {
		
		return   cliente.consumoEnergeticoTotal() > getConsumoMinimo ()  && 
				cliente.consumoEnergeticoTotal() <= getConsumoMaximo();  }
	
	
	
	public Double getCargoFijo() { return this.cargo_fijo; }
	public Double getCargoVariable() { return this.cargo_variable; }
	
	public Double calcularCostosPara(Cliente cliente) {
		Categoria categoria = cliente.getCategoria();
		return categoria.getCargoFijo() + categoria.getCargoVariable() * cliente.consumoEnergeticoTotal();
	}
}