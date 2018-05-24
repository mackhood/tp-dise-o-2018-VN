package Clases.repositories;




public class RepositorioCategoria <Categoria> extends Repositorio<Categoria> {

    String archivo = "Categoria.json";

    private static RepositorioCategoria instance = new RepositorioCategoria();

    private RepositorioCategoria() {
    }

    public static RepositorioCategoria getInstance() {
        return instance;
    }


}

