package Clases;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AsignarCategoria {
	
	Categoria cat1 = new R1();
	Categoria cat2 = new R2();
	Categoria cat3 = new R3();
	Categoria cat4 = new R4();
	Categoria cat5 = new R5();
	Categoria cat6 = new R6();
	Categoria cat7 = new R7();
	Categoria cat8 = new R8();
	Categoria cat9 = new R9();
	
	List <Categoria> categoriasPosibles = Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9);
	
	public Categoria definirCategoriaPara(Cliente cliente) {
		
		return categoriasPosibles.stream().filter(cat -> cat.cumpleCondicion(cliente)).collect(Collectors.toList()).get(0);
	}
	
}
