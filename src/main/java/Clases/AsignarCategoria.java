package Clases;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import Clases.repositories.RepositorioCategoria;
public class AsignarCategoria {
	
RepositorioCategoria repositorio = RepositorioCategoria.getInstance();
	
	List <Categoria> categoriasPosibles = repositorio.obtenerCategorias();
	
	public Categoria definirCategoriaPara(Cliente cliente) {
		
		return categoriasPosibles.stream().filter(cat -> cat.cumpleCondicion(cliente)).collect(Collectors.toList()).get(0);
	}
	
}
