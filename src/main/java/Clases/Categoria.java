package Clases;

import static java.util.Objects.isNull;

public class Categoria {
    private String nombreCategoria;
    private double consumoMinimo;
    private double consumoMaximo;
    private Double cargoFijo;
    private Double cargoVariable;

    public Categoria(String nombreCategoria, Integer consumoMinimo, Integer consumoMaximo, Double cargo_fijo, Double cargoVariable) {
        this.nombreCategoria = nombreCategoria;
        this.consumoMinimo = consumoMinimo;
        this.consumoMaximo = consumoMaximo;
        this.cargoFijo = cargo_fijo;
        this.cargoVariable = cargoVariable;
    }

    private Double getConsumoMinimo() {

        return this.consumoMinimo;
    }

    public Double getConsumoMaximo() {

        if (isNull(consumoMaximo))
            return  0.0;
        return this.consumoMaximo;
    }


    public boolean cumpleCondicion(Cliente cliente) {
    	
    	//Si consumo maximo es null quiere decir que cualquier numero es menor a este
        return cliente.consumoEnergeticoTotal() >= getConsumoMinimo() &&
                (getConsumoMaximo() == null || cliente.consumoEnergeticoTotal() <= getConsumoMaximo());
    }


    private Double getCargoFijo() {
        return this.cargoFijo;
    }

    public Double getCargoVariable() {
        return this.cargoVariable;
    }

    public Double calcularCostosPara(Cliente cliente) {
         return this.getCargoFijo() + this.getCargoVariable() * cliente.consumoEnergeticoTotal();
    }
    public String getNombreCategoria(){
        return this.nombreCategoria;
    }

}