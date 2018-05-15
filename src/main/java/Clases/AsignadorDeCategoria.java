package Clases;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import Clases.entities.ProcessingDataFailedException;
import Clases.repositories.RepositorioCategoria;

public class AsignadorDeCategoria {

    public static AsignadorDeCategoria instance = new AsignadorDeCategoria();

    private AsignadorDeCategoria() {
    }

    RepositorioCategoria repositorio = RepositorioCategoria.getInstance();

    public Categoria definirCategoriaPara(Cliente cliente) throws ProcessingDataFailedException {

        List<Categoria> categoriasPosibles = null;
        try {
            categoriasPosibles = repositorio.obtenerCategorias();
        } catch (ProcessingDataFailedException e) {
            throw new ProcessingDataFailedException(e.getLocalizedMessage());
        }

        return categoriasPosibles.stream().filter(cat -> cat.cumpleCondicion(cliente)).collect(Collectors.toList()).get(0);
    }

}
