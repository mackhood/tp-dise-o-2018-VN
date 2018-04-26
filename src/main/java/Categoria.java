package domain;

public interface Categoria {

	boolean cumpleCondicion(Cliente cliente);
	
	double getCostoFijo();
	
	double getCostoVariable();
}
