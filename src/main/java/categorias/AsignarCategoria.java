package categorias;
import java.util.stream.Collectors;

import categorias.*;
import clases.*;

public class AsignarCategoria
{
	
	public Categoria categoriaDelCliente(Cliente unCliente)
	{
		ResidenciasConstantes x = new ResidenciasConstantes();
		
		return  x.devolverCategoriaQueCumpleElCliente(unCliente);
	}
}
