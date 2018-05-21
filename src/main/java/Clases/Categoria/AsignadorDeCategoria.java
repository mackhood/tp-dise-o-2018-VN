package Clases.Categoria;

import Clases.Usuario.Cliente;
import Clases.entities.ProcessingDataFailedException;
import Clases.repositories.RepositorioCategoria;

import java.util.List;
import java.util.stream.Collectors;

public class AsignadorDeCategoria {

    private static AsignadorDeCategoria instance = new AsignadorDeCategoria();

    private AsignadorDeCategoria() {
    }

    RepositorioCategoria repositorio = RepositorioCategoria.getInstance();

    public static AsignadorDeCategoria getInstance() {
        return instance;
    }

    public Categoria definirCategoriaPara(Cliente cliente) throws ProcessingDataFailedException {

        List<Categoria> categoriasPosibles = null;
        try {
            categoriasPosibles = repositorio.obtenerCategorias();
        } catch (ProcessingDataFailedException e) {
            throw new ProcessingDataFailedException(e.getLocalizedMessage());
        }
        return categoriaCliente(cliente, categoriasPosibles);

    }

    public Categoria categoriaCliente(Cliente cliente, List<Categoria> categoriasPosibles) {
        return categoriasPosibles.stream().filter(cat -> cat.cumpleCondicion(cliente)).collect(Collectors.toList()).get(0);
    }

}
