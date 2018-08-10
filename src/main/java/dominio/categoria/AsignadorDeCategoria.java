package dominio.categoria;

import dominio.usuario.Cliente;
import dominio.entities.ProcessingDataFailedException;
import dominio.repositories.RepositorioCategoria;

import java.util.List;
import java.util.stream.Collectors;

public class AsignadorDeCategoria {

    private static AsignadorDeCategoria instance = new AsignadorDeCategoria();

    public AsignadorDeCategoria() {
    
    }

    //Cambios para poder usar mock y spy sin necesidad de llamar al verdadero repo
    //O usar un archivo json de prueba.
    public RepositorioCategoria getObtenerRepositorio(){
         return  RepositorioCategoria.getInstance();
    }
    public List <Categoria> getObtenerCategoriasDelRepositorio() throws ProcessingDataFailedException{
        try {
            return this.getObtenerRepositorio().obtenerCategorias();
        } catch (ProcessingDataFailedException e) {
            throw new ProcessingDataFailedException(e.getLocalizedMessage());
        }
    }
    public static AsignadorDeCategoria getInstance() {
        return instance;
    }

    public Categoria definirCategoriaPara(Cliente cliente) throws ProcessingDataFailedException {

        List <Categoria> categoriasPosibles = this.getObtenerCategoriasDelRepositorio();

        return categoriaCliente(cliente, categoriasPosibles);

    }

    public Categoria categoriaCliente(Cliente cliente, List<Categoria> categoriasPosibles) {
        return categoriasPosibles.stream().filter(cat -> cat.cumpleCondicion(cliente)).collect(Collectors.toList()).get(0);
    }

	public void actualizarPara(Cliente cliente) {

		Categoria cat = null;
		
		try {
		
			cat = this.definirCategoriaPara(cliente);
		}
		
		catch (ProcessingDataFailedException e) {
			
		}
		
		cliente.setCategoria(cat);
	}
}